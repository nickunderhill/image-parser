package com.agileengine.imageparser.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClearCacheTask {

    private static final Logger log = LoggerFactory.getLogger(ClearCacheTask.class);

    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedRateString = "${cache.time-to-live.milliseconds}", initialDelayString = "${cache.time-to-live.milliseconds}")
    public void clearCacheBySchedule() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> Objects.requireNonNull(cacheManager.getCache(name)).clear());
        log.info("Cache has been cleared by scheduled task");
    }
}
