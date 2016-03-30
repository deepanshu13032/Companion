package com.example.deepanshuarora.companion.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepanshuarora on 30/03/16.
 */
public class LoadFoodData {
    public List<Food> data;

    // List<String> available_cuisines = new ArrayList<>(Arrays.asList("Chinese", "Continental", "North Indian", "South Indian", "Korean","","","","","","","","","",""));
    public LoadFoodData() {
        data = new ArrayList<Food>();
        data.add(new Food("http://i.ndtvimg.com/i/2015-09/chilli-chicken-625_625x350_41441399214.jpg", "Big Wong", "A casual dining place with an ambience of a classy pan asian restaurant. Rush here for the widely acclaimed dimsums and sushi. The place opens from 11 am to 11 pm",
                28.551870, 77.251786, "http://i.ndtvimg.com/i/2015-09/chilli-chicken-625_625x350_41441399214.jpg", "https://media-cdn.tripadvisor.com/media/photo-s/03/34/77/5f/jack-s-chinese-restaurant.jpg "));
        data.add(
                new Food("http://i.imgur.com/WKEl7vp.jpg",
                        "Juneja Roll", "Hidden in the lanes of Chitranjan park is an eatery where you would find the best melt in your mouth rolls/wraps. They also make curries and a variety of north indian food. Open from 1 PM to 4 PM, 6 PM to 11 PM",
                        28.540879, 77.249542,
                        "http://i.imgur.com/WKEl7vp.jpg",
                        "http://i.imgur.com/iaB8AtI.jpg"));
        data.add( new Food( "http://i.imgur.com/vOuCAwQ.jpg", "Om Corner", "In the bustling heart of the largest electronic market of Nehru place, this place serves hot and spicy “cholle bathure” at a very affordable price. Served with fresh pickle this place has office goes licking their fingers. Open from 11:30 am to 2:30 pm", 28.549165, 77.252956, "http://i.imgur.com/vOuCAwQ.jpg", "http://i.imgur.com/uRulpID.jpg"));
        data.add( new Food( "http://i.imgur.com/mRK3y53.jpg", "The Grill Kitchen", "Healthy, hygenic ,authentic north indian and mughali food. The place has a wide variety of kababs. The place is open from 11 am to 11 pm", 28.538259, 77.259627, "http://i.imgur.com/mRK3y53.jpg", "http://i.imgur.com/Z1xdq5j.jpg"));
//        data.add(new Food("http://i.imgur.com/WKEl7vp.jpg","Juneja Roll"));
//        data.add(new Food("http://i.ndtvimg.com/i/2015-09/chilli-chicken-625_625x350_41441399214.jpg","Big Wong"));
//        data.add(new Food("http://i.imgur.com/vOuCAwQ.jpg","Om Chole Corner"));
//        data.add(new Food("http://i.imgur.com/mRK3y53.jpg","The grill kitchen"));
    }
}

