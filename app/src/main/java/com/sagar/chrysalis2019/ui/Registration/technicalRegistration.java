package com.sagar.chrysalis2019.ui.Registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sagar.chrysalis2019.R;

import java.util.zip.Inflater;

public class technicalRegistration extends Fragment {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Technical Team");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_technical_registration,container,false);
        final EditText etTeamName,etFirstPName,etSecondPName,etThirdPName,etContactNo;
        Button btnRegister;


        etTeamName = root.findViewById(R.id.et_teamName_techReg);
        etFirstPName =  root.findViewById(R.id.et_firstPersonName_techReg);
        etSecondPName =  root.findViewById(R.id.et_secondPersonName_techReg);
        etThirdPName =  root.findViewById(R.id.et_thirdPersonName_techReg);
        btnRegister = root.findViewById(R.id.btn_Register_techReg);
        etContactNo =  root.findViewById(R.id.et_contactNo_techReg);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(etTeamName.getText().toString().trim().length()==0 || etFirstPName.getText().toString().trim().length()==0 || etSecondPName.getText().toString().trim().length()==0 ||
                        etThirdPName.getText().toString().trim().length()==0 || etContactNo.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getContext(),"Please Fill All Fields",Toast.LENGTH_SHORT).show();

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
                                myRef.child(etTeamName.getText().toString()).child("Contact Number").setValue(etContactNo.getText().toString());

                                Toast.makeText(getContext(), "Technical Registration Successfull.", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), "Error!!", Toast.LENGTH_SHORT).show();


                        }
                    });









                }

            }
        });


        return root;
    }
}
