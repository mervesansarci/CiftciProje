package com.denemeler.projeciftci;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfilFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_profil, container, false);



        RecyclerView rv= v.findViewById(R.id.rv_tarla);
        ArrayList<Tarlalar> eklenenTarla;
        rvAdapter adapter;
        Button btnTarlaEkle = v.findViewById(R.id.btn_tarlaekle);
        //EditText txtTarlaEkle= v.findViewById(R.id.txt_tarlaismiEkle);
        TextView isim=v.findViewById(R.id.txtIsım);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        eklenenTarla = new ArrayList<>();

        Tarlalar k1 = new Tarlalar("tarla1","tekirdağ");
        Tarlalar k2 = new Tarlalar("tarla1","tekirdağ");

        



        eklenenTarla.add(k1);
        eklenenTarla.add(k2);


        adapter= new rvAdapter(getActivity(),eklenenTarla);
        rv.setAdapter(adapter);


        btnTarlaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertGoster();


            }
        });
        return v;
    }
    public void alertGoster(){
        LayoutInflater layout= LayoutInflater.from(getActivity());

        View tasarim= layout.inflate(R.layout.alert_tarlaekle,null);

        EditText tarlaEkle= tasarim.findViewById(R.id.tarlaAd);
        EditText tarlaKonum=tasarim.findViewById(R.id.tarlaKonum);

        AlertDialog.Builder ad= new AlertDialog.Builder(getActivity());
        ad.setTitle("Tarla Ekle");
        ad.setView(tasarim);
        ad.setPositiveButton("ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String tarla_ad= tarlaEkle.getText().toString().trim();
                String tarla_konum= tarlaKonum.getText().toString().trim();
                Toast.makeText(getActivity(), "ekkklendi", Toast.LENGTH_SHORT).show();

            }
        });
        ad.setNegativeButton("iptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.create().show();

    }

}
