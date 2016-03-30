package com.example.deepanshuarora.companion.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepanshuarora on 30/03/16.
 */
public class LoadFoodData {
    public List<Food> data;
   // List<String> available_cuisines = new ArrayList<>(Arrays.asList("Chinese", "Continental", "North Indian", "South Indian", "Korean","","","","","","","","","",""));
    public LoadFoodData(){
        data = new ArrayList<Food>();
        data.add(new Food("http://i.imgur.com/vOuCAwQ.jpg", "Om Corner","In the bustling heart of the largest electronic market of Nehru place, this place serves hot and spicy “cholle bathure” at a very affordable price. Served with fresh pickle this place has office goes licking their fingers. Open from 11:30 am to 2:30 pm",28.549165, 77.252956,"http://i.imgur.com/uRulpID.jpg"));
//        data.add(new Food("http://i.imgur.com/WKEl7vp.jpg","Juneja Roll"));
//        data.add(new Food("http://i.ndtvimg.com/i/2015-09/chilli-chicken-625_625x350_41441399214.jpg","Big Wong"));
//        data.add(new Food("http://i.imgur.com/vOuCAwQ.jpg","Om Chole Corner"));
//        data.add(new Food("http://i.imgur.com/mRK3y53.jpg","The grill kitchen"));
    }
}

