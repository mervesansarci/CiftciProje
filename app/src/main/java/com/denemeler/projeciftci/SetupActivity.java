package com.denemeler.projeciftci;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class SetupActivity extends AppCompatActivity {


    private CircleImageView profileView;
    private Uri mainImageURI= null;
    private String user_id;
    private EditText setupName, setupSurname, setupBD,setupNo;
    private Button btnSetup;
    private ProgressBar setupProgress;
    private UploadTask.TaskSnapshot taskSnapshot;
    private Spinner setupSpinner;
    private FirebaseFirestore db;
    private Toolbar setupToolbar;
    private RadioButton radioCiftci,radioMuhendis;
    private RadioGroup radioGroup;
    private TextView seciliStatu;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Kullanicilar");


    private FirebaseStorage storageRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        //firebase
        firebaseAuth= FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        mUser= firebaseAuth.getCurrentUser();
        storageRef=FirebaseStorage.getInstance();

        profileView= findViewById(R.id.profile_image);
        setupName=findViewById(R.id.setup_name);
        setupSurname=findViewById(R.id.setup_surname);
        setupBD=findViewById(R.id.setup_bd);
        setupNo=findViewById(R.id.setup_muhNo);
        btnSetup=findViewById(R.id.btn_setup);
        setupProgress= findViewById(R.id.setupprogressBar);
        radioCiftci=findViewById(R.id.radioCiftci);
        radioMuhendis=findViewById(R.id.radioMuhendis);
        seciliStatu=findViewById(R.id.seciliStatu);



        //setupToolbar=findViewById(R.id.setupToolbar);
        //setSupportActionBar(setupToolbar);
        //setupToolbar.setTitle("Hesap Bilgileri");
        //setupToolbar.setBackgroundColor(Color.parseColor("#288319"));
        //setupToolbar.setTitleTextColor(Color.parseColor("#f8f8f8"));

        String user_name= setupName.getText().toString().trim();
        String user_surname=setupSurname.getText().toString().trim();
        String user_bd=setupBD.getText().toString().trim();
        String user_email= firebaseAuth.getCurrentUser().getEmail().trim();
        user_id= firebaseAuth.getCurrentUser().getUid();

        setupNo.setVisibility(View.INVISIBLE);


        radioCiftci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupNo.setVisibility(View.INVISIBLE);
                seciliStatu.setText(radioCiftci.getText());

            }
        });

        radioMuhendis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupNo.setVisibility(View.VISIBLE);
                seciliStatu.setText(radioMuhendis.getText());


            }
        });


        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                switch (selectedId){
//
//                    case R.id.radioCiftci:
//                        Toast.makeText(getApplicationContext(), "AAAAAAAAAA", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.radioMuhendis:
//                        setupNo.setVisibility(View.VISIBLE);
//                        break;
//
//                    default:
//                        setupNo.setVisibility(View.INVISIBLE);
//
//                }

                String user_name= setupName.getText().toString().trim();
                String user_surname=setupSurname.getText().toString().trim();
                String user_bd=setupBD.getText().toString().trim();
                String user_statu=seciliStatu.getText().toString().trim();
                String user_email= firebaseAuth.getCurrentUser().getEmail().trim();
                user_id= firebaseAuth.getCurrentUser().getUid();
                Map<String, Object> kullanici = new HashMap<>();
                kullanici.put("profilresmi",mainImageURI.toString());
                kullanici.put("kullaniciID",user_id);
                kullanici.put("eposta",user_email);
                kullanici.put("adi",user_name);
                kullanici.put("soyadi",user_surname);
                kullanici.put("dogumGunu",user_bd);
                kullanici.put("çiftçi mi mühendis mi",user_statu);


                Intent intent = new Intent(SetupActivity.this,MainActivity.class);
                intent.putExtra("Ad",user_name); //veri gönderiliyor
                intent.putExtra("Ad",user_surname);
                startActivity(intent);


                db.collection("kullanici").add(kullanici).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"eklendi",Toast.LENGTH_LONG).show();
                        Intent mainIntent= new Intent(SetupActivity.this,MainActivity.class);
                        startActivity(mainIntent);

                    }
                });
                if (!TextUtils.isEmpty(user_name) &&!TextUtils.isEmpty(user_surname) && !TextUtils.isEmpty(user_bd) && mainImageURI!=null){

                    StorageReference imagePath= storageRef.getReference().child("profil_fotolari").child(user_id+"+ jpg");
                    imagePath.putFile(mainImageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                UploadTask.TaskSnapshot downloadUri = task.getResult();

                            }

                            else{

                            }
                        }
                    });
                }
            }

            private void ImageAdd() {
                if (!TextUtils.isEmpty(user_name) &&!TextUtils.isEmpty(user_surname) && !TextUtils.isEmpty(user_bd) && mainImageURI!=null){
                    StorageReference imagePath= storageRef.getReference().child("profil_fotolari").child(user_id+"+ jpg");
                    imagePath.putFile(mainImageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){

                                UploadTask.TaskSnapshot downloadUri = task.getResult();
                            }
                            else{

                            }
                        }
                    });
                }
                else{

                }
            }
        });

        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP ){
                    if(ContextCompat.checkSelfPermission(SetupActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(SetupActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(SetupActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

                    }
                    else {

                        BringImagePicker();
                    }
                }
                else{
                    BringImagePicker();
                }
            }

            private void BringImagePicker() {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1,1).start(SetupActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mainImageURI = result.getUri();
                profileView.setImageURI(mainImageURI);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }
}