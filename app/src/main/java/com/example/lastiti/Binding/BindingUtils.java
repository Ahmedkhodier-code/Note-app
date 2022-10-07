package com.example.lastiti.Binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;


public class BindingUtils {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, int url) {
        view.setImageResource(url);
    }
}
