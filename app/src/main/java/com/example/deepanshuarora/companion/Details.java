package com.example.deepanshuarora.companion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

public class Details extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;

    private TextView name,description;
    private String str_name,str_desc;
    private HashMap<String,String> url_maps;
    private double def = 12.343242;
    private double lat,lng;
    SimpleDraweeView mapview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.desc);
        Intent rec = this.getIntent();
        str_name = rec.getStringExtra("name");
        str_desc = rec.getStringExtra("desc");
        lat = rec.getDoubleExtra("latitude", def);
        lng = rec.getDoubleExtra("longitude",def);
        mapview = (SimpleDraweeView) findViewById(R.id.preview);
        mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent(v.getContext(), MapsActivity.class);
                z.putExtra("name",str_name);
                z.putExtra("lati",lat);
                z.putExtra("long",lng);
                v.getContext().startActivity(z);
            }
        });

        name.setText(str_name);
        description.setText(str_desc);

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        url_maps = (HashMap<String, String>) rec.getSerializableExtra("hashmapimages");
        url_maps.put("ExtraImg", rec.getStringExtra("coverimage"));
        //url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        //url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        //url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information


            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        mDemoSlider.setPresetTransformer("Default");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
