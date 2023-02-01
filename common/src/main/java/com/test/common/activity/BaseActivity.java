package com.test.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.test.common.application.MIntent;
import com.test.common.ultilities.StringUtils;

public abstract class BaseActivity<P extends ActivityContract.Presenter, B extends ViewDataBinding> extends AppCompatActivity
        implements ActivityContract.View<P> {
    private P mPresenter;
    protected B mBinding;
    private String TAG = BaseActivity.this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingContentView(getLayoutResId());
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        onCreateBindingView(savedInstanceState);
    }

    protected String getScreenName() {
        return this.getClass().getSimpleName();
    }

    protected void onActivityStart(MIntent mIntent) {}

    protected abstract void onCreateBindingView(@Nullable Bundle savedInstanceState);

    protected void setBindingContentView(@LayoutRes int layoutId) {
        mBinding = DataBindingUtil.setContentView(this, layoutId);
    }
    @Override
    public P initPresenter() {
        return null;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void showMessage(String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int stringResId) {
        Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show();
    }

    protected abstract int getLayoutResId();
    public MIntent getMIntent() {
        return new MIntent(getIntent());
    }
    public void setActivityResult(int resultCode, MIntent mIntent) {
        if (mIntent == null) {
            super.setResult(resultCode, null);
        } else {
            super.setResult(resultCode, mIntent.getIntent());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onActivityForResult(requestCode, resultCode, data == null ? null : new MIntent(data));
    }
    public void onActivityForResult(int requestCode, int resultCode, @Nullable MIntent data) {}

    @Override
    protected void onStop() {
        super.onStop();
    }
}
