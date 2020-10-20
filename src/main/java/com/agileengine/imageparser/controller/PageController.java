package com.agileengine.imageparser.controller;

import com.agileengine.imageparser.dto.PageDto;
import com.agileengine.imageparser.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/images")
public class PageController {

    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    PageDto getPage(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request) {
        log.info("Endpoint triggered: {}", request.getRequestURI());
        return pageService.getPageById(page);
    }
}
