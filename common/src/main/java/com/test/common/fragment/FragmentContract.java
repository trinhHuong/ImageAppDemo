/*
 * OTT Video, Android Smart TV Application
 * Copyright (c) 2020 Viettel Corporation . All rights reserved.
 * Last modified 7/16/20 3:17 PM
 */

package com.test.common.fragment;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public interface FragmentContract {
    interface Interactor<P extends Presenter> {
        P getPresenter();
    }

    interface View<P extends Presenter> {
        P getPresenter();
        void setPresenter(P presenter);
        P initPresenter();
        Context getContext();
        void showMessage(String message);
        void showMessage(@StringRes int messageResId);
    }

    interface Presenter<V extends View, I extends Interactor> {
        I initInteractor();
        V getView();
        I getInteractor();
        Fragment getFragment();
        Context getContext();
        String getString(int resID, Object... params);
        void requestSuccess();
        void requestFail(String message);
        boolean isLoading();
        void setLoading(boolean loading);
    }
}
