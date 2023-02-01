package com.test.common.network.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huongtrinh on 1/30/23.
 */
public class SearchResult implements Serializable {
    private int page;
    private int per_page;
    private int total_results;
    private String next_page;
    private List<PhotoDetail> photos;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public String getNext_page() {
        return next_page;
    }

    public void setNext_page(String next_page) {
        this.next_page = next_page;
    }

    public List<PhotoDetail> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDetail> photos) {
        this.photos = photos;
    }
}
