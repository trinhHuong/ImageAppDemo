/*
 * OTT Video, Android Smart TV Application
 * Copyright (c) 2020 Viettel Corporation . All rights reserved.
 * Last modified 7/16/20 3:19 PM
 */

package com.test.common.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.test.common.activity.BaseActivity;
import com.test.common.ultilities.StringUtils;

/**
 * Created by huongtrinh on 2020-07-16.
 */
public abstract class BaseFragment <B extends ViewDataBinding, P extends FragmentContract.Presenter> extends Fragment
        implements FragmentContract.View<P>{
    private P mPresenter;
    protected B mBinding;
    protected String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void setContentView(LayoutInflater inflater, int layoutResID, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, layoutResID, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setContentView(inflater, getLayoutResId(), container);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }


    @Override
    public void showMessage(@StringRes int messageResId) {
        Toast.makeText(getContext(), messageResId, Toast.LENGTH_SHORT).show();
    }
    public void displayFragment(Fragment fragment, int containerID) {
        if (fragment == null || !this.isAdded()) {
            return;
        }
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(containerID, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
    public void removeFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
    public void hideFragment(Fragment fragment, int containerID) {
        if (fragment == null) {
            return;
        }
        if (fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void showMessage(String message) {
        if (getContext() == null) {
            return;
        }
        if (StringUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected abstract int getLayoutResId();
    protected void onActivityKeyDown(int keycode, int idFocusedView) {}
    public void replaceFragment(Fragment fragment, int containerID) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction()
                .replace(containerID, fragment);
        transaction.commitAllowingStateLoss();
    }



}
