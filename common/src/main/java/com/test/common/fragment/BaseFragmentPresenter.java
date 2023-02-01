package com.test.common.fragment;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.Fragment;

import com.test.common.ultilities.StringUtils;
public abstract class BaseFragmentPresenter<V extends FragmentContract.View, I extends FragmentContract.Interactor> extends BaseObservable
        implements FragmentContract.Presenter<V, I> {
    private V mView;
    private I mInteractor;
    private boolean isLoading = false;
    private boolean isShowViewNoResult = false;

    public BaseFragmentPresenter(V mView) {
        this.mView = mView;
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
    public Fragment getFragment() {
        return (Fragment) mView;
    }

    @Override
    public Context getContext() {
        return mView.getContext();
    }

    @Override
    public String getString(int resID, Object... params) {
        return getContext() == null ?  null : getContext().getString(resID, params);
    }

    @Override
    public void requestSuccess() {
        setLoading(false);
    }

    @Override
    public void requestFail(String message) {
        setLoading(false);
        if (!StringUtils.isEmpty(message)) {
            getView().showMessage(message);
        }
    }

    @Bindable
    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void setLoading(boolean loading) {
        isLoading = loading;
    }

}