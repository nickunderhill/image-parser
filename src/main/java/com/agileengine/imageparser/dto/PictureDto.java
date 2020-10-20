package com.agileengine.imageparser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PictureDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("cropped_picture")
    private String croppedPicture;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("cropped_picture")
    public String getCroppedPicture() {
        return croppedPicture;
    }

    @JsonProperty("cropped_picture")
    public void setCroppedPicture(String croppedPicture) {
        this.croppedPicture = croppedPicture;
    }
}