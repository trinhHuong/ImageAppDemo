package com.test.common.activity;

import android.content.Context;

import androidx.annotation.StringRes;
public interface ActivityContract {
    interface Interactor<P extends Presenter> {
        P getPresenter();
    }

    interface View<P extends Presenter> {
        P initPresenter();

        P getPresenter();

        void showMessage(String message);

        void showMessage(@StringRes int stringResId);
    }

    interface Presenter<V extends View, I extends Interactor> {

        V getView();

        I initInteractor();

        I getInteractor();

        void attachView(V view);

        Context getContext();

        String getString(int resID, Object... params);
    }
}
