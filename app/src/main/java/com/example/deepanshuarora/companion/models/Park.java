package com.example.deepanshuarora.companion.models;

/**
 * Created by deepanshuarora on 30/03/16.
 */
public class Park {
    String imgurl;
    String name;
    public Park(String imgurl, String name){
        this.imgurl = imgurl;
        this.name = name;
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
