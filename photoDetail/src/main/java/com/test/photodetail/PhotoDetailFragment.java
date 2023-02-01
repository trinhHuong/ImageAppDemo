package com.test.photodetail;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.test.common.application.Constant;
import com.test.common.application.MIntent;
import com.test.common.application.MKeys;
import com.test.common.fragment.BaseFragment;
import com.test.common.ultilities.MSharedSession;
import com.test.photodetail.databinding.FragmentPhotoDetailBinding;

/**
 * Created by huongtrinh on 1/31/23.
 */
public class PhotoDetailFragment extends BaseFragment<FragmentPhotoDetailBinding, PhotoDetailFragmentContract.Presenter> implements PhotoDetailFragmentContract.View {
    private PhotoDetailPager photoDetailPager;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_photo_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photoDetailPager = new PhotoDetailPager(getContext());
        mBinding.viewPager.setAdapter(photoDetailPager);
        mBinding.viewPager.setCurrentItem(MSharedSession.getInstance().getInt(MKeys.ITEM_POSITION));
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MSharedSession.getInstance().putInt(MKeys.ITEM_POSITION, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBinding.fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MIntent mIntent = new MIntent(Constant.ACTION_REMOVE_PHOTO);
                mIntent.putInt(MKeys.ITEM_POSITION, mBinding.viewPager.getCurrentItem());
                getBaseActivity().sendBroadcast(mIntent);
                photoDetailPager.removeItem(mBinding.viewPager.getCurrentItem());
                Toast.makeText(getContext(), "Delete item: " + mBinding.viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().getSupportActionBar().hide();
    }

    @Override
    public PhotoDetailFragmentContract.Presenter initPresenter() {
        return new PhotoDetailFragmentPresenter(this);
    }
}
