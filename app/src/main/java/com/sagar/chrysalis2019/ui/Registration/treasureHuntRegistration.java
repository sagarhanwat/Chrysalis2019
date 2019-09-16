package com.sagar.chrysalis2019.ui.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sagar.chrysalis2019.R;
import com.sagar.chrysalis2019.ui.login.LoginActivity;

public class treasureHuntRegistration extends Fragment {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Treasure Hunt Team");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_tresurehunt_registration, container,false);

        final EditText etTeamName,etFirstPName,etSecondPName,etThirdPName,etFourthPName,etFifthPName,etContactNo;
        Button btnRegister;

        etTeamName = root.findViewById(R.id.et_teamName_treasureHuntReg);
        etFirstPName =  root.findViewById(R.id.et_firstPersonName_treasureHuntReg);
        etSecondPName =  root.findViewById(R.id.et_secondPersonName_treasureHuntReg);
        etThirdPName =  root.findViewById(R.id.et_thirdPersonName_treasureHuntReg);
        etFourthPName =  root.findViewById(R.id.et_fourthPersonName_treasureHuntReg);
        etFifthPName =  root.findViewById(R.id.et_fifthPersonName_treasureHuntReg);

        btnRegister = root.findViewById(R.id.btn_Register_treasureHuntReg);
        etContactNo =  root.findViewById(R.id.et_contactNo_treasureHuntReg);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null){
            Intent i= new Intent(getContext(), LoginActivity.class);
            startActivity(i);
        }
        else {


            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(etTeamName.getText().toString().trim().length()==0 || etFirstPName.getText().toString().trim().length()==0 || etSecondPName.getText().toString().trim().length()==0 ||
                            etThirdPName.getText().toString().trim().length()==0 || etContactNo.getText().toString().trim().length()==0 || etFifthPName.getText().toString().trim().length()==0 ||etFourthPName.getText().toString().trim().length()==0)
                    {
                        Toast.makeText(getContext(),"Please Fill All Fields.",Toast.LENGTH_SHORT).show();
                    }
                    else {




                        myRef.orderByChild("Team Name").equalTo(etTeamName.getText().toString()).addValueEventListener(new ValueEventListener(){
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                                if(dataSnapshot.exists()){
                                    Toast.makeText(getActivity(),"Team Name alrady exists.",Toast.LENGTH_SHORT).show();
                                    etTeamName.setText("");
                                }else{


                                    myRef.child(etTeamName.getText().toString()).child("Team Name").setValue(etTeamName.getText().toString());
                                    myRef.child(etTeamName.getText().toString()).child("First Person Name").setValue(etFirstPName.getText().toString());
                                    myRef.child(etTeamName.getText().toString()).child("Second Person Name").setValue(etSecondPName.getText().toString());
                                    myRef.child(etTeamName.getText().toString()).child("Third Person Name").setValue(etThirdPName.getText().toString());
                                    myRef.child(etTeamName.getText().toString()).child("Fourth Person Name").setValue(etFourthPName.getText().toString());
                                    myRef.child(etTeamName.getText().toString()).child("Fifth Person Name").setValue(etFifthPName.getText().toString());
                                    myRef.child(etTeamName.getText().toString()).child("Contact Number").setValue(etContactNo.getText().toString());
                                    myRef.child(etTeamName.getText().toString()).child("Registration Id").setValue(user.getEmail());

                                    Toast.makeText(getContext(), " Treasure Hunt Registration Successfull.", Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getContext(), "Error.", Toast.LENGTH_SHORT).show();


                            }
                        });






                    }
                }
            });


        }


        return root;
    }





}


