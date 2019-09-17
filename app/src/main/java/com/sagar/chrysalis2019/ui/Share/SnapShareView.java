package com.sagar.chrysalis2019.ui.Share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sagar.chrysalis2019.ImageAdapter;
import com.sagar.chrysalis2019.R;

import java.util.ArrayList;
import java.util.List;

public class SnapShareView extends Fragment {

    private RecyclerView mRecycleView;


    private List<uploadImgae> mUpload;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("UploadImage");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUpload = new ArrayList<uploadImgae>();
                for(DataSnapshot Ds:dataSnapshot.getChildren()){
                    uploadImgae uploadImgae1=Ds.getValue(uploadImgae.class);
                    mUpload.add(uploadImgae1);
                }

                ImageAdapter img=new ImageAdapter(mUpload);
                mRecycleView.setAdapter(img);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_share_view,container,false);

        mRecycleView=root.findViewById(R.id.recycleview_image_share);


        return root;
    }
}
