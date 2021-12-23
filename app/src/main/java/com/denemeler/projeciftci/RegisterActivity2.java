package com.denemeler.projeciftci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity2 extends AppCompatActivity {

    private EditText reg_loginEmailText, reg_loginPassText,reg_confirm_passText;
    private Button reg_btn, reg_login_btn;
    private FirebaseAuth mAut;
    private FirebaseUser fAut;
    private ProgressBar regprogressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mAut= FirebaseAuth.getInstance();

        //mFirestore= FirebaseFirestore.getInstance();

        reg_loginEmailText=findViewById(R.id.reg_email);
        reg_loginPassText=findViewById(R.id.reg_confirm);
        reg_confirm_passText=findViewById(R.id.reg_confirm);
        reg_btn=findViewById(R.id.btn_reg);
        reg_login_btn=findViewById(R.id.btn_reg_login);
        regprogressBar=findViewById(R.id.reg_progress);


        reg_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= reg_loginEmailText.getText().toString();
                String pass= reg_loginPassText.getText().toString();
                String confirmPass= reg_confirm_passText.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confirmPass)){
                    if (pass.equals(confirmPass)){
                        regprogressBar.setVisibility(View.VISIBLE);

                        mAut.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    FirebaseUser user=mAut.getCurrentUser();
                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(@NonNull Void unused) {


                                            Toast.makeText(getApplicationContext(), "Kayıt oluşturuldu. E-posta doğrulama için lütfen e-postanızı kontrol edin.", Toast.LENGTH_SHORT).show();
                                            Intent logIntent= new Intent(RegisterActivity2.this,SetupActivity.class);
                                            startActivity(logIntent);


                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });

                                }
                                else{
                                    String errorMessage= task.getException().getMessage();
                                    Toast.makeText(RegisterActivity2.this, "Error: "+errorMessage, Toast.LENGTH_SHORT).show();


                                }
                                regprogressBar.setVisibility(View.INVISIBLE);

                            }

                        });


                    }
                    else{
                        Toast.makeText(RegisterActivity2.this, "parola uyuşmuyor", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    private void sentToMain() {

        Intent mainIntent= new Intent(RegisterActivity2.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    }
