package com.test.photodetail;

import com.test.common.fragment.FragmentContract;

/**
 * Created by huongtrinh on 1/31/23.
 */
public interface PhotoDetailFragmentContract {
    interface View extends FragmentContract.View<Presenter> {
    }
    interface Presenter extends FragmentContract.Presenter<View, Interactor>{
    }
    interface Interactor extends FragmentContract.Interactor<Presenter>{
    }
}
