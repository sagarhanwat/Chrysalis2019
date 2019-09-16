package com.sagar.chrysalis2019;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private ArrayList<String> myValues;
    public RecyclerViewAdapter (ArrayList<String> myValues){
        this.myValues= myValues;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       android.view.View listItem;
        listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            holder.myTextView.setText(myValues.get(position));
        }catch (Exception e) {

        }

    }


    @Override
    public int getItemCount() {
        return myValues.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextView;
        MyViewHolder(View itemView) {
            super(itemView);
            try {
                myTextView =myTextView.findViewById(R.id.TV_cardview_desc_anunccement);
            }catch (Exception e) {
                //Toast.makeText(getClass(),"failed in Recycle view adapter",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
