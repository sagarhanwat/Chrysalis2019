package com.sagar.chrysalis2019.ui.Share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sagar.chrysalis2019.R;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class SnapShare extends Fragment {

    private EditText EtImgUploadText;
    private Button BtnImgUpload,btnChooseImg;
    private ImageView imgViewUpload;
    public static final int PICK_IMAGE = 1;

    private FirebaseStorage storage=FirebaseStorage.getInstance();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_share, container, false);

        EtImgUploadText=root.findViewById(R.id.ET_img_uploadText);
        BtnImgUpload=root.findViewById(R.id.btn_img_upload);
        imgViewUpload=root.findViewById(R.id.ImgUPload);
        btnChooseImg=root.findViewById(R.id.btn_choose_img);




        btnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });


       BtnImgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    funUploadImg();
            }
        });

        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imgViewUpload.setImageURI(selectedImage);
                }

    }
    private void funUploadImg(){
        Bitmap capture =Bitmap.createBitmap(
                imgViewUpload.getWidth(),
                imgViewUpload.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas captureCanvas =new Canvas(capture);
        imgViewUpload.draw(captureCanvas);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        capture.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[] data =outputStream.toByteArray();
        BtnImgUpload.setEnabled(false);
        String path="SnapShare/"+ UUID.randomUUID()+".png";
        final StorageReference SnapShareRef=storage.getReference(path);
        StorageMetadata metadata=new StorageMetadata.Builder()
                .setCustomMetadata("Caption",EtImgUploadText.getText().toString())
                .build();
        UploadTask uploadTask = SnapShareRef.putBytes(data,metadata);

        uploadTask.addOnCompleteListener(getActivity(), new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(getActivity(),"Upload Successfull",Toast.LENGTH_SHORT).show();

                BtnImgUpload.setEnabled(true);

            }
        });

        Task<Uri> getDownloadUriTask = uploadTask.continueWithTask(
                new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                        return SnapShareRef.getDownloadUrl();
                    }
                }
        );
        getDownloadUriTask.addOnCompleteListener(getActivity(), new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Uri downloadUri = task.getResult();
                    uploadImgae upload=new uploadImgae(EtImgUploadText.getText().toString(),downloadUri.toString());
                      FirebaseDatabase database = FirebaseDatabase.getInstance();
                      DatabaseReference myRef = database.getReference("UploadImage");
                      String uploadId=myRef.push().getKey();
                      myRef.child(uploadId).setValue(upload);
                }
            }
        });

    }





}
