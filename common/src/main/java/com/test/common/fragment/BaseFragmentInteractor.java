package com.test.common.fragment;
public abstract class BaseFragmentInteractor <P extends FragmentContract.Presenter>
        implements FragmentContract.Interactor<P>{
    private P mPresenter;

    public BaseFragmentInteractor(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

}
