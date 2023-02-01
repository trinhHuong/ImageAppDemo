package com.test.common.network.models;

import java.io.Serializable;

/**
 * Created by huongtrinh on 1/30/23.
 */
public class Resource implements Serializable {
    private String landscape;
    private String portrait;

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }
}
