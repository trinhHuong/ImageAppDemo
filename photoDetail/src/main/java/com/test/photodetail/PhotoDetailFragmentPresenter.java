package com.test.photodetail;

import com.test.common.fragment.BaseFragmentPresenter;

/**
 * Created by huongtrinh on 1/31/23.
 */
public class PhotoDetailFragmentPresenter extends BaseFragmentPresenter<PhotoDetailFragmentContract.View, PhotoDetailFragmentContract.Interactor> implements PhotoDetailFragmentContract.Presenter {
    public PhotoDetailFragmentPresenter(PhotoDetailFragmentContract.View mView) {
        super(mView);
    }

    @Override
    public PhotoDetailFragmentContract.Interactor initInteractor() {
        return null;
    }
}
