package com.agileengine.imageparser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDto {

    @JsonProperty("pictures")
    private List<PictureDto> pictures = null;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("pageCount")
    private Integer pageCount;
    @JsonProperty("hasMore")
    private Boolean hasMore;

    @JsonProperty("pictures")
    public List<PictureDto> getPictures() {
        return pictures;
    }

    @JsonProperty("pictures")
    public void setPictures(List<PictureDto> pictures) {
        this.pictures = pictures;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("pageCount")
    public Integer getPageCount() {
        return pageCount;
    }

    @JsonProperty("pageCount")
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("hasMore")
    public Boolean getHasMore() {
        return hasMore;
    }

    @JsonProperty("hasMore")
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}