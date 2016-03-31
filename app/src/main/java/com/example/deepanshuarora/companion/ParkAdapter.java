package com.example.deepanshuarora.companion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepanshuarora.companion.models.LoadFoodData;
import com.example.deepanshuarora.companion.models.LoadParkData;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by karan barsiwal on 29-03-2016.
 */
public class ParkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    //private List<String> cuisines;
    //private Context context;
    LoadParkData loadData =new LoadParkData();


    public ParkAdapter( Context context) {

        inflater = LayoutInflater.from(context);
    }



    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.mycard_view, parent, false);
        RecyclerView.ViewHolder fc = new MyViewHolder(v);

        return fc;
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder)holder).image.setImageURI(Uri.parse(loadData.data.get(position).getImgurl()));
        ((MyViewHolder)holder).name.setText(loadData.data.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return loadData.data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        //public CardView cardView;
        TextView name;
        SimpleDraweeView image;
        public MyViewHolder(View v) {
            super(v);
            name=(TextView) v.findViewById(R.id.place_name);
            image =(SimpleDraweeView) v.findViewById(R.id.my_image_view);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Details.class);
                    i.putExtra("name",loadData.data.get(getAdapterPosition()).getName());
                    //i.putExtra("desc",loadData.data.get(getAdapterPosition()).getDesc());
                    i.putExtra("latitude",loadData.data.get(getAdapterPosition()).getX());
                    i.putExtra("longitude",loadData.data.get(getAdapterPosition()).getY());
                    i.putExtra("hashmapimages",loadData.data.get(getAdapterPosition()).getSub_image());
                    i.putExtra("coverimage",loadData.data.get(getAdapterPosition()).getImgurl());
                    i.putExtra("control",0);
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
