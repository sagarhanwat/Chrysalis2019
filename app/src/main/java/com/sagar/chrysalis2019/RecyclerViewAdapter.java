package com.sagar.chrysalis2019;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private ArrayList<announcement> list;
    public RecyclerViewAdapter (ArrayList<announcement> list){
        this.list=list;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.TVdescription.setText(list.get(position).getDescription());
        holder.TVtittle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TVtittle;
        TextView TVdescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TVdescription= itemView.findViewById(R.id.TV_cardview_desc_anunccement);
            TVtittle=itemView.findViewById(R.id.TV_cardview_heading_anunccement);
        }
    }


}