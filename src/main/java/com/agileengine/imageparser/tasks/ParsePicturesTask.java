package com.agileengine.imageparser.tasks;

import com.agileengine.imageparser.domain.Picture;
import com.agileengine.imageparser.service.impl.AgileEngineApiParsingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParsePicturesTask {

    private static final Logger log = LoggerFactory.getLogger(ParsePicturesTask.class);

    private final AgileEngineApiParsingService agileEngineApiParsingService;

    public ParsePicturesTask(AgileEngineApiParsingService agileEngineApiParsingService) {
        this.agileEngineApiParsingService = agileEngineApiParsingService;
    }

    @Scheduled(initialDelayString = "${parsing.frequency.time.milliseconds}", fixedRateString = "${parsing.frequency.time.milliseconds}")
    public void scheduledParseTask() {
        log.info("Starting scheduled data update...");
        List<Picture> pictureList = agileEngineApiParsingService.parseAllPictures();
        agileEngineApiParsingService.persistParsedPicturesToDb(pictureList);
    }
}
