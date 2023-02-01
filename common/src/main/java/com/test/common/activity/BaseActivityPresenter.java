
package com.test.common.activity;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
public abstract class BaseActivityPresenter <V extends ActivityContract.View, I extends ActivityContract.Interactor> extends BaseObservable
        implements ActivityContract.Presenter<V, I> {
    private V mView;
    private I mInteractor;
    private boolean isLoading = false;

    public BaseActivityPresenter() {
        mInteractor = initInteractor();
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public I getInteractor() {
        return mInteractor;
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public Context getContext() {
        return (Context) mView;
    }

    @Override
    public String getString(int resID, Object... params) {
        return getContext() == null ?  null : getContext().getString(resID, params);
    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

//    public void setLoading(boolean loading) {
//        isLoading = loading;
//        notifyPropertyChanged(BR.loading);
//    }
}
