package com.agileengine.imageparser.service.impl;

import com.agileengine.imageparser.client.AgileEngineImageApi;
import com.agileengine.imageparser.dto.PageDto;
import com.agileengine.imageparser.service.PageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl implements PageService {

    private final AgileEngineImageApi agileEngineImageApi;

    public PageServiceImpl(AgileEngineImageApi agileEngineImageApi) {
        this.agileEngineImageApi = agileEngineImageApi;
    }

    @Override
    @Cacheable("pages")
    public PageDto getPageById(int page) {
        return agileEngineImageApi.fetchResponseByPageNumber(page);
    }
}
