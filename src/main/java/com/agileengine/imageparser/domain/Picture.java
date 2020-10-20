package com.agileengine.imageparser.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.StringJoiner;

@Entity
@Indexed
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Picture {

    @Id
    private String id;

    @Field
    private String author;

    @Field
    private String camera;

    @Field
    private String tags;

    @Column(name = "cropped_picture")
    private String croppedPicture;

    @Column(name = "full_picture")
    private String fullPicture;

    public Picture() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCroppedPicture() {
        return croppedPicture;
    }

    public void setCroppedPicture(String croppedPicture) {
        this.croppedPicture = croppedPicture;
    }

    public String getFullPicture() {
        return fullPicture;
    }

    public void setFullPicture(String fullPicture) {
        this.fullPicture = fullPicture;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Picture.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("author='" + author + "'")
                .add("camera='" + camera + "'")
                .add("tags='" + tags + "'")
                .add("croppedPicture='" + croppedPicture + "'")
                .add("fullPicture='" + fullPicture + "'")
                .toString();
    }
}