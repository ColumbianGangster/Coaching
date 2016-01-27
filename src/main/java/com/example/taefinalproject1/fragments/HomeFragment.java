package com.example.taefinalproject1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taefinalproject1.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TAE_user2 on 27/01/2016.
 */
public class HomeFragment extends Fragment {
    @Bind(R.id.home_image_view)
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container,false);
        ButterKnife.bind(this, v);
        return v;
    }
}
