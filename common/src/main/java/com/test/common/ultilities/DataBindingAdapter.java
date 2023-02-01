package com.test.common.ultilities;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapter {
    @BindingAdapter("android:srcUrl")
    public static void setSrc(ImageView imageView, String url) {
        ImageUtils.getInstance().loadImageNormal(imageView.getContext(), url, imageView);
    }
    @BindingAdapter("android:srcRounded")
    public static void setSrcRounded(ImageView imageView, String url) {
        ImageUtils.getInstance().loadRoundedImage(imageView.getContext(), url, imageView);
    }
    @BindingAdapter("android:selected")
    public static void setSelected(View view, boolean isSelected) {
        view.setSelected(isSelected);
    }
}
