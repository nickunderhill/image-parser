package com.agileengine.imageparser.controller;

import com.agileengine.imageparser.client.AgileEngineImageApi;
import com.agileengine.imageparser.domain.Picture;
import com.agileengine.imageparser.dto.PageDto;
import com.agileengine.imageparser.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@EnableCaching
@RequestMapping("/images")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    private final PictureService pictureService;
    private final AgileEngineImageApi agileEngineImageApi;

    public ImageController(PictureService pictureService, AgileEngineImageApi agileEngineImageApi) {
        this.pictureService = pictureService;
        this.agileEngineImageApi = agileEngineImageApi;
    }

    @GetMapping
    PageDto getAllPictures(HttpServletRequest request) {
        log.info("Endpoint triggered: {}", request.getRequestURI());
        return agileEngineImageApi.fetchResponseByPageNumber(1);
    }
}
