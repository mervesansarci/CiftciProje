package com.denemeler.projeciftci;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class YeniTarlaActivity extends AppCompatActivity {

    EditText editTextTarlaAdi, editTextTarlaKonum;
    androidx.appcompat.widget.Toolbar toolbarYT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_tarla);

        editTextTarlaAdi=findViewById(R.id.tarlaAd);
        editTextTarlaKonum=findViewById(R.id.tarlaKonum);
        toolbarYT = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarYT);
        toolbarYT.setTitle("Tarla Ekle");
        toolbarYT.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbarYT);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tarla_kaydet,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_tarlaKaydet:
                tarlaKaydet();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }



    }

    private void tarlaKaydet() {
        String tarlaAd= editTextTarlaAdi.getText().toString();
        String tarlaKonumu=editTextTarlaKonum.getText().toString();

        //final DocumentReference kisilerRef = FirebaseFirestore.getInstance().collection("KullaniciBilgileri").document();


        CollectionReference tarlaRef= FirebaseFirestore.getInstance().collection("Tarlalar");


        tarlaRef.add(new Tarlalar(tarlaAd,tarlaKonumu));
        Toast.makeText(getApplicationContext(), "Eklendi", Toast.LENGTH_SHORT).show();
        finish();


    }
}