package com.agileengine.imageparser.service;

import com.agileengine.imageparser.domain.Picture;

import java.util.List;

public interface PictureService {

    List<Picture> searchPicturesByMetadata(String searchTerm);

    List<Picture> getAll();
}
