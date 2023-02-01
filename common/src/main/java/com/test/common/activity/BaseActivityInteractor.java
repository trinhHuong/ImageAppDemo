package com.test.common.activity;
public abstract class BaseActivityInteractor <P extends ActivityContract.Presenter>
        implements ActivityContract.Interactor<P> {
    private P mPresenter;

    public BaseActivityInteractor(P presenter) {
        mPresenter = presenter;
    }
    @Override
    public P getPresenter() {
        return mPresenter;
    }
}