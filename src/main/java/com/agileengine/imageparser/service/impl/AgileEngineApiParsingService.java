package com.agileengine.imageparser.service.impl;

import com.agileengine.imageparser.client.AgileEngineImageApi;
import com.agileengine.imageparser.domain.Picture;
import com.agileengine.imageparser.dto.PageDto;
import com.agileengine.imageparser.dto.PictureDto;
import com.agileengine.imageparser.repository.PictureRepository;
import com.agileengine.imageparser.service.ParsingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgileEngineApiParsingService implements ParsingService {

    private static final Logger log = LoggerFactory.getLogger(AgileEngineApiParsingService.class);
    private final AgileEngineImageApi api;
    private final PictureRepository pictureRepository;
    private final PictureDtoEntityConverterService dtoEntityConverter;

    public AgileEngineApiParsingService(AgileEngineImageApi api, PictureRepository pictureRepository, PictureDtoEntityConverterService dtoEntityConverter) {
        this.api = api;
        this.pictureRepository = pictureRepository;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    @PostConstruct
    private void init() {
        log.info("Parsing API on startup...");
        parseAll();
    }

    @Scheduled(initialDelayString = "${parsingFrequencyMilliseconds}", fixedRateString = "${parsingFrequencyMilliseconds}")
    public void scheduleFixedDelayTask() {
        log.info("Starting scheduled data update...");
        parseAll();
    }

    @Override
    public void parseAll() {
        int currentPage = 1;
        PageDto page = api.fetchResponseByPageNumber(currentPage);
        log.info("Parsing all data. Total pages: {}...", page.getPageCount());
        if (page.getPictures().size() > 0) {
            List<PictureDto> pictureDtos = new ArrayList<>(page.getPictures());
            while (page.getHasMore()) {
                page = api.fetchResponseByPageNumber(++currentPage);
                pictureDtos.addAll(new ArrayList<>(page.getPictures()));
            }
            log.info("{} pictures fetched! Parsing picture details...", pictureDtos.size());
            List<Picture> pictures =  new ArrayList<>();
            for (PictureDto pictureDto:pictureDtos) {
                pictures.add(dtoEntityConverter.dtoToEntity(api.fetchResponseByPictureId(pictureDto.getId())));
            }
            long deltaEntries = pictureRepository.count();
            pictureRepository.saveAll(pictures);
            deltaEntries = pictureRepository.count() - deltaEntries;
            log.info("{} new pictures added to the database", deltaEntries);
        } else {
            log.info("Nothing to save.");
        }
    }
}
