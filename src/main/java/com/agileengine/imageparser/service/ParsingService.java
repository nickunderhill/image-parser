package com.agileengine.imageparser.service;

import com.agileengine.imageparser.domain.Picture;

import java.util.List;

public interface ParsingService {

    void init();

    List<Picture> parseAllPictures();

}
