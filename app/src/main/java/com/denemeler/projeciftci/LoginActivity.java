package com.denemeler.projeciftci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private final  String[] statu={"Mühendis","Çiftçi"};

    private EditText loginEmailText, loginPassText;
    private Button loginBtn, loginRegBtn;
    private FirebaseAuth mAut;
    private ProgressBar loginprogressBar;

    private Spinner loginSpinner;
    private ArrayAdapter<String> adapterStatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAut= FirebaseAuth.getInstance();

        loginEmailText= findViewById(R.id.login_email);
        loginPassText=findViewById(R.id.login_pass);
        loginBtn=findViewById(R.id.btn_login);
        loginRegBtn=findViewById(R.id.btn_reg_login);
        loginprogressBar=(ProgressBar) findViewById(R.id.login_progress);
        loginSpinner=findViewById(R.id.loginSpinner);
        adapterStatu= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,statu);
        loginSpinner.setAdapter(adapterStatu);

        loginSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals(statu[0])){


                }
                else if (parent.getSelectedItem().toString().equals(statu[1])){

                    sendToMain();
        }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail= loginEmailText.getText().toString();
                String loginPass= loginPassText.getText().toString();

                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass) ){
                    loginprogressBar.setVisibility(View.VISIBLE);
                    mAut.signInWithEmailAndPassword(loginEmail,loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                if(mAut.getCurrentUser().isEmailVerified()){
                                    sendToMain();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "E-postanızı doğrulayın.", Toast.LENGTH_SHORT).show();

                                }

                            }
                            else{
                                String errorMessage= task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error!"+errorMessage, Toast.LENGTH_SHORT).show();
                            }
                            loginprogressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });


        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent= new Intent(LoginActivity.this,RegisterActivity2.class);
                startActivity(regIntent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser= mAut.getCurrentUser();

        if (currentUser!=null){
            sendToMain();
        }
    }

    private void sendToMain() {
        Intent mainIntent= new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    public void sendToEngineerMain(){

    }

}