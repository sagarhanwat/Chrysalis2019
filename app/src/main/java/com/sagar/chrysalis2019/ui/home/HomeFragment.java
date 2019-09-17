package com.sagar.chrysalis2019.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sagar.chrysalis2019.R;
import com.sagar.chrysalis2019.RecyclerViewAdapter;
import com.sagar.chrysalis2019.announcement;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;;

    private ArrayList<announcement> list;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
    //    homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView=root.findViewById(R.id.recycleview_home);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("announcement");



       if(myRef!=null){

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list= new ArrayList<announcement>();
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            announcement A1 =dataSnapshot1.getValue(announcement.class);
                            list.add(A1);

                        }

                        RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(list);
                        recyclerView.setAdapter(recyclerViewAdapter);



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}