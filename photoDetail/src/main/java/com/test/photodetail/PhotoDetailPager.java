package com.test.photodetail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.google.gson.Gson;
import com.test.common.network.models.PhotoDetail;
import com.test.common.ultilities.CachedDataManager;
import com.test.photodetail.databinding.ItemPhotoDetailBinding;

import java.util.List;

/**
 * Created by huongtrinh on 1/31/23.
 */
public class PhotoDetailPager extends PagerAdapter {
    private List<PhotoDetail> photoDetails;
    private LayoutInflater inflater;

    public PhotoDetailPager(Context context) {
        photoDetails = CachedDataManager.getInstance().getPhotos();
        Log.e(getClass().getSimpleName(), "Photos: " + new Gson().toJson(photoDetails));
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return photoDetails.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ConstraintLayout) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ItemPhotoDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_photo_detail, container, false);
        binding.setViewModel(photoDetails.get(position));
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
    public void removeItem(int position) {
        photoDetails.remove(position);
        notifyDataSetChanged();
    }
}
