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

@RestController
@RequestMapping("/images")
public class PictureController {

    private static final Logger log = LoggerFactory.getLogger(PictureController.class);

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{id}")
    Picture getPictureDetails(@PathVariable String id, HttpServletRequest request) {
        log.info("Endpoint triggered: {}", request.getRequestURI());
        return pictureService.getPictureDetails(id);
    }
}
