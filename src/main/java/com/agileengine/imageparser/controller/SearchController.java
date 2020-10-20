package com.agileengine.imageparser.controller;

import com.agileengine.imageparser.domain.Picture;
import com.agileengine.imageparser.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    private final PictureService pictureService;

    public SearchController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{keyword}")
    List<Picture> getPicturesByKeyword(@PathVariable String keyword, HttpServletRequest request) {
        log.info("Endpoint triggered: {}", request.getRequestURI());
        return pictureService.searchPicturesByMetadata(keyword);
    }
}
