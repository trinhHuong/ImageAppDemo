package com.test.imageapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.test.common.activity.ActivityContract;
import com.test.common.activity.BaseActivity;
import com.test.imageapp.databinding.ActivityHomeBinding;

/**
 * Created by huongtrinh on 1/18/23.
 */
public class MainActivity extends BaseActivity<ActivityContract.Presenter, ActivityHomeBinding> {
    @Override
    protected void onCreateBindingView(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController().navigateUp();
    }
}
