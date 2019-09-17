package com.sagar.chrysalis2019;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.chrysalis2019.ui.Share.uploadImgae;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    private List<uploadImgae> imgUploadList;

    public ImageAdapter(List<uploadImgae> list) {

        imgUploadList=list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.share_image_cardview,parent,false);


        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.TV_img_caption.setText(imgUploadList.get(position).getCaption());
        Picasso.get()
                .load(imgUploadList.get(position).getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imgView_card);

    }

    @Override
    public int getItemCount() {
        return imgUploadList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView TV_img_caption;
        public ImageView imgView_card;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_img_caption=itemView.findViewById(R.id.TV_shareCardView);
            imgView_card=itemView.findViewById(R.id.ImgView_Share_cardView);

        }
    }
}
