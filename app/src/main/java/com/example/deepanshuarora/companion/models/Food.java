package com.example.deepanshuarora.companion.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**

 * Created by deepanshuarora on 30/03/16.
 */
public class Food {
    String imgurl;
    String name;

    public HashMap<String, String> getSub_image() {
        return sub_image;
    }

    HashMap<String,String> sub_image;
    String desc;

    public LatLng getOrigin() {
        return origin;
    }

    private LatLng origin;
    public Food(String imgurl, String name,String desc,Float a,Float b,String sub_url1,String sub_url2){
        this.sub_image=new HashMap<String, String>();
        this.imgurl = imgurl;
        this.name = name;
        this.desc=desc;
        this.origin= new LatLng(a,b);
        this.sub_image.put("1", sub_url1);
        this.sub_image.put("2", sub_url2);
    }
    public String getDesc() {
        return desc;
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
