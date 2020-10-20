package com.agileengine.imageparser.client;

import com.agileengine.imageparser.dto.PageDto;
import com.agileengine.imageparser.dto.PictureDetailsDto;
import com.agileengine.imageparser.dto.TokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AgileEngineImageApi {

    private static final Logger log = LoggerFactory.getLogger(AgileEngineImageApi.class);

    private final static String API_KEY = "23567b218376f79d9415";
    private final static String API_BASE_URL = "http://interview.agileengine.com";
    private final static String API_PATH_AUTH = "/auth";
    private final static String API_PATH_IMAGES = "/images";
    private final static String API_PARAM_PAGE = "?page=";

    private final RestTemplate restTemplate;
    private static TokenDto tokenDto = null;

    public AgileEngineImageApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    private void init() {
        getToken();
    }

    private void getToken() {
        log.info("Obtaining API token...");
        String constructUrl = API_BASE_URL + API_PATH_AUTH;
        Map<String, String> map = new HashMap<>();
        map.put("apiKey", API_KEY);
        ResponseEntity<TokenDto> auth = restTemplate.postForEntity(API_BASE_URL + API_PATH_AUTH, map, TokenDto.class);
        log.info("API call: [status code: {}, url: {}]", auth.getStatusCode(), constructUrl);
        tokenDto = auth.getBody();
        log.info("Token id: {}", tokenDto.getToken());
    }

    public PageDto fetchResponseByPageNumber(Integer pageNumber) {
        if (!tokenDto.isAuth()) getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenDto.getToken());
        String constructUrl = API_BASE_URL + API_PATH_IMAGES + API_PARAM_PAGE + pageNumber;
        ResponseEntity<PageDto> page = restTemplate
                .exchange(constructUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        PageDto.class);
        if (page.getStatusCode().equals(HttpStatus.OK)) {
            log.info("API call: [status code: {}, url: {}]", page.getStatusCode(), constructUrl);
        } else {
            log.error("API call failed: [status code: {}, url: {}]", page.getStatusCode(), constructUrl);
        }
        return page.getBody();
    }

    public PictureDetailsDto fetchResponseByPictureId(String id) {
        if (!tokenDto.isAuth()) getToken();
        String constructUrl = API_BASE_URL + API_PATH_IMAGES + "/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenDto.getToken());
        ResponseEntity<PictureDetailsDto> page = restTemplate
                .exchange(constructUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        PictureDetailsDto.class);
        if (page.getStatusCode().equals(HttpStatus.OK)) {
            log.info("API call: [status code: {}, url: {}]", page.getStatusCode(), constructUrl);
        } else {
            log.error("API call failed: [status code: {}, url: {}]", page.getStatusCode(), constructUrl);
        }
        return page.getBody();
    }
}
