package com.agileengine.imageparser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PictureDetailsDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("author")
    private String author;
    @JsonProperty("camera")
    private String camera;
    @JsonProperty("tags")
    private String tags;
    @JsonProperty("cropped_picture")
    private String croppedPicture;
    @JsonProperty("full_picture")
    private String fullPicture;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("camera")
    public String getCamera() {
        return camera;
    }

    @JsonProperty("camera")
    public void setCamera(String camera) {
        this.camera = camera;
    }

    @JsonProperty("tags")
    public String getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(String tags) {
        this.tags = tags;
    }

    @JsonProperty("cropped_picture")
    public String getCroppedPicture() {
        return croppedPicture;
    }

    @JsonProperty("cropped_picture")
    public void setCroppedPicture(String croppedPicture) {
        this.croppedPicture = croppedPicture;
    }

    @JsonProperty("full_picture")
    public String getFullPicture() {
        return fullPicture;
    }

    @JsonProperty("full_picture")
    public void setFullPicture(String fullPicture) {
        this.fullPicture = fullPicture;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PictureDetailsDto.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("author='" + author + "'")
                .add("camera='" + camera + "'")
                .add("tags='" + tags + "'")
                .add("croppedPicture='" + croppedPicture + "'")
                .add("fullPicture='" + fullPicture + "'")
                .toString();
    }
}