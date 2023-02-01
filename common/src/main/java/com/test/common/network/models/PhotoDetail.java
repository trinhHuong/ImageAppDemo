package com.test.common.network.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.test.common.BR;

import java.io.Serializable;

/**
 * Created by huongtrinh on 1/30/23.
 */
public class PhotoDetail extends BaseObservable implements Serializable {
    private String id;
    private String photographer;
    private Resource src;
    private boolean isSelected;

    @Bindable
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifyPropertyChanged(BR.selected);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotographer() {
        return "By " + photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public Resource getSrc() {
        return src;
    }

    public void setSrc(Resource src) {
        this.src = src;
    }
}
