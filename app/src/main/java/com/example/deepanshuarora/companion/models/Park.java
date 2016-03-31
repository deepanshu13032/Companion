package com.example.deepanshuarora.companion.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by deepanshuarora on 30/03/16.
 */
public class Park {
    String imgurl;
    String name;
    double x;

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    double y;
    public HashMap<String, String> getSub_image() {
        return sub_image;
    }

    HashMap<String,String> sub_image;

    public LatLng getOrigin() {
        return origin;
    }

    private LatLng origin;
    public Park(String imgurl, String name,double a,double b,String sub_url1){
        this.sub_image=new HashMap<String, String>();
        this.imgurl = imgurl;
        this.name = name;
        this.x=a;
        this.y=b;
        this.sub_image.put("1", sub_url1);
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
