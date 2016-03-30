package com.example.deepanshuarora.companion;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by karan barsiwal on 29-03-2016.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    //private LayoutInflater inflater;
    private List<String> cuisines;
    private Context context;


    public FoodAdapter(List<String> cuisines, Context context) {
        this.context = context;
        this.cuisines = cuisines;
     //   inflater = LayoutInflater.from(context);
    }



    public FoodAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mycard_view, parent, false);
        MyViewHolder vh = new MyViewHolder((CardView) v);

        return vh;
    }


    public void onBindViewHolder(FoodAdapter.MyViewHolder holder, int position) {
        holder.cardView.setTag(holder.cardView.getId(),position);
        ((TextView)holder.cardView.findViewById(R.id.cuis_name)).setText(cuisines.get(position));

    }

    @Override
    public int getItemCount() {
        return cuisines.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public MyViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
