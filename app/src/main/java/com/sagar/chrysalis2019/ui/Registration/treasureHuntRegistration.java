package com.sagar.chrysalis2019.ui.Registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sagar.chrysalis2019.R;

import java.util.zip.Inflater;

public class treasureHuntRegistration extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_tresurehunt_registration, container,false);
        return root;
    }
}
