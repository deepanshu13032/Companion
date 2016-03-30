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
        data.add( new Park( "http://i.imgur.com/RIJhcDQ.jpg", "Kalkaji district park", 28.556694, 77.262785, "http://i.imgur.com/VQLxDIR.jpg"));
        data.add( new Park( "http://i.imgur.com/YElpicS.png", "Astha kunj park", 28.553016, 77.254187, "http://i.imgur.com/9JP2xZt.jpg"));
        data.add( new Park( "http://i.imgur.com/YElpicS.png", "Astha kunj park", 28.553016, 77.254187, "http://i.imgur.com/9JP2xZt.jpg"));
        data.add( new Park( "http://i.imgur.com/3jfNuu6.png", "Kailash hills main park", 28.556908, 77.257697, "http://i.imgur.com/irNp0wU.jpg"));

    }
}
