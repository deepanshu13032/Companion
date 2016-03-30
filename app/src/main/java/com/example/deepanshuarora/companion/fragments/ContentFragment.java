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
import com.example.deepanshuarora.companion.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";
    private static int xmlswitcher;
    private View containerView, cv2;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;

    public static ContentFragment newInstance(int resId, int type) {
        xmlswitcher = type;
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (xmlswitcher == 2) {
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
        List<String> available_cuisines;
        if (xmlswitcher == 2) {
            rootView = inflater.inflate(R.layout.foodscreen, container, false);
            mrecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            mLayoutmanager = new LinearLayoutManager(getActivity());
            mrecyclerView.setLayoutManager(mLayoutmanager);
            available_cuisines = new ArrayList<>(Arrays.asList("Chinese", "Continental", "North Indian", "South Indian", "Korean","","","","","","","","","",""));
            mAdapter = new FoodAdapter(available_cuisines,getActivity());
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