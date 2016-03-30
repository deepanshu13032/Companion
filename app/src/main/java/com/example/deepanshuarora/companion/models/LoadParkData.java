package com.example.deepanshuarora.companion.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepanshuarora on 30/03/16.
 */
public class LoadParkData {
    public List<Park> data;
    // List<String> available_cuisines = new ArrayList<>(Arrays.asList("Chinese", "Continental", "North Indian", "South Indian", "Korean","","","","","","","","","",""));
    public LoadParkData(){
        data = new ArrayList<Park>();
        data.add(new Park("http://i.imgur.com/RIJhcDQ.jpg","Kalkaji District Park"));
        data.add(new Park("http://i.imgur.com/YElpicS.png","Astha Kunj Park"));
        data.add(new Park("http://i.imgur.com/zLL8yBq.png","Shyam nagar park"));
        data.add(new Park("http://i.imgur.com/3jfNuu6.png","Kailash hills main park"));

    }
}
