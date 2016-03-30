package com.example.deepanshuarora.companion.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.deepanshuarora.companion.FoodAdapter;
import com.example.deepanshuarora.companion.ParkAdapter;
import com.example.deepanshuarora.companion.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String FOOD = "Food";
    public static final String PARK = "Park";


    private static int xmlswitcher;
    private View containerView, cv2;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;

    public static ContentFragment newInstance( int type) {
        xmlswitcher = type;
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (xmlswitcher == 1 || xmlswitcher == 2) {
            this.containerView = view.findViewById(R.id.container2);
        } else {
            this.containerView = view.findViewById(R.id.container);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        if (xmlswitcher == 1 || xmlswitcher == 2) {
            rootView = inflater.inflate(R.layout.foodscreen, container, false);
            mrecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            mLayoutmanager = new LinearLayoutManager(getActivity());
            mrecyclerView.setLayoutManager(mLayoutmanager);

            if (xmlswitcher == 1) {
                mAdapter = new FoodAdapter(this.getContext());
            } else {
                mAdapter = new ParkAdapter(this.getContext());
            }
            mrecyclerView.setAdapter(mAdapter);

        } else {
            rootView = inflater.inflate(R.layout.fragment_check_one, container, false);
            mImageView = (ImageView) rootView.findViewById(R.id.image_content);
            mImageView.setClickable(true);
            mImageView.setFocusable(true);
            mImageView.setImageResource(res);
        }
        return rootView;


    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}