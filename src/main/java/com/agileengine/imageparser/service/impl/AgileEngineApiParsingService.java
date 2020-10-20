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
    @Override
    public void init() {
        log.info("Parsing API on startup...");
        List<Picture> pictureList = parseAllPictures();
        persistParsedPicturesToDb(pictureList);
    }

    @Scheduled(initialDelayString = "${parsingFrequencyMilliseconds}", fixedRateString = "${parsingFrequencyMilliseconds}")
    public void scheduledParseTask() {
        log.info("Starting scheduled data update...");
        List<Picture> pictureList = parseAllPictures();
        persistParsedPicturesToDb(pictureList);
    }

    @Override
    public List<Picture> parseAllPictures() {
        log.info("Parsing pictures from API...");
        List<String> pictureIdList = parsePictureIdsFromAllPages();

        log.info("Parsing details for {} pictures...", pictureIdList.size());
        return pictureIdList.stream()
                .map(id -> dtoEntityConverter.dtoToEntity(api.fetchResponseByPictureId(id)))
                .collect(Collectors.toList());
    }

    public List<String> parsePictureIdsFromAllPages() {
        int currentPage = 1;
        PageDto page = api.fetchResponseByPageNumber(currentPage);

        log.info("Parsing picture ids from {} pages...", page.getPageCount());
        List<String> pictureIds = page.getPictures()
                .stream()
                .map(PictureDto::getId)
                .collect(Collectors.toList()
                );

        while (page.getHasMore()) {
            pictureIds.addAll(page.getPictures().stream()
                    .map(PictureDto::getId)
                    .collect(Collectors.toList())
            );

            page = api.fetchResponseByPageNumber(++currentPage);
        }

        log.info("{} picture ids retrieved!", pictureIds.size());

        return pictureIds;
    }

    public void persistParsedPicturesToDb(List<Picture> pictures) {
        log.info("Persisting fetched pictures to database...");
        long deltaEntries = pictureRepository.count();
        pictureRepository.saveAll(pictures);
        deltaEntries = pictureRepository.count() - deltaEntries;
        log.info("{} new pictures added to the database", deltaEntries);
    }
}
