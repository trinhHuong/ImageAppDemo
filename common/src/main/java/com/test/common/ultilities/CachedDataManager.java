package com.test.common.ultilities;

import com.test.common.application.MKeys;
import com.test.common.network.models.PhotoDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huongtrinh on 1/31/23.
 */
public class CachedDataManager {
    private static CachedDataManager instance;

    public synchronized static CachedDataManager getInstance() {
        if (instance == null) {
            instance = new CachedDataManager();
        }
        return instance;
    }

    public void savePhotos(List<PhotoDetail> photoDetails) {
        if (photoDetails == null) {
            return;
        }
        List<PhotoDetail> photos = MSharedSession.getInstance().getObject(MKeys.LIST_PHOTOS);
        if (photos == null) {
            photos = new ArrayList<>();
        }
        photos.addAll(photoDetails);
        MSharedSession.getInstance().putObject(MKeys.LIST_PHOTOS, (Serializable) photos);
    }
    public void clearPhotos() {
        MSharedSession.getInstance().remove(MKeys.LIST_PHOTOS);
    }
    public List<PhotoDetail> getPhotos() {
        List<PhotoDetail> photos = MSharedSession.getInstance().getObject(MKeys.LIST_PHOTOS);
        if (photos == null) {
            return new ArrayList<>();
        }
        return photos;
    }
}
