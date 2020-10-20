package com.agileengine.imageparser.service.impl;

import com.agileengine.imageparser.domain.Picture;
import com.agileengine.imageparser.dto.PictureDetailsDto;
import com.agileengine.imageparser.service.DtoEntityConverterService;
import org.springframework.stereotype.Service;

@Service
public class PictureDtoEntityConverterService implements DtoEntityConverterService<Picture, PictureDetailsDto> {

    @Override
    public PictureDetailsDto entityToDto(Picture picture) {
        PictureDetailsDto dto = new PictureDetailsDto();
        dto.setId(picture.getId());
        dto.setAuthor(picture.getAuthor());
        dto.setCamera(picture.getCamera());
        dto.setTags(picture.getTags());
        dto.setCroppedPicture(picture.getCroppedPicture());
        dto.setFullPicture(picture.getFullPicture());
        return dto;
    }

    @Override
    public Picture dtoToEntity(PictureDetailsDto dto) {
        Picture picture = new Picture();
        picture.setId(dto.getId());
        picture.setAuthor(dto.getAuthor());
        picture.setCamera(dto.getCamera());
        picture.setTags(dto.getTags());
        picture.setCroppedPicture(dto.getCroppedPicture());
        picture.setFullPicture(dto.getFullPicture());
        return picture;
    }
}