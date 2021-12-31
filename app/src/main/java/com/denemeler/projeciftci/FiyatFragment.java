package com.denemeler.projeciftci;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FiyatFragment extends Fragment {

    WebView wv;
    Spinner spinnerBolge;
    String[] bolgeler = {"Babaeski","Balıkesir","Bandırma","Biga","Çanakkale","Çorlu","Edirne","Edremit",
            "Gönen","Hayrabolu","İpsala","Keşan","Kırklareli","Lüleburgaz","Malkara","Susurluk","Tekirdağ","Uzunköprü"};

    ArrayAdapter adpBolge;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fiyatliste, container, false);

        wv = (WebView) v.findViewById(R.id.wv);
        wv.setWebViewClient(new WebViewClient());
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);

        spinnerBolge = (Spinner) v.findViewById(R.id.spinnerBolge);


        adpBolge = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, bolgeler);
        adpBolge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBolge.setAdapter(adpBolge);
        islemYap();

        return v;



    }

    public void islemYap(){
        spinnerBolge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getSelectedItem().toString().equals(bolgeler[0]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5BA01");
                else if(parent.getSelectedItem().toString().equals(bolgeler[1]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5BA10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[2]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5BA20");
                else if(parent.getSelectedItem().toString().equals(bolgeler[3]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5BI10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[4]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5CA10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[5]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5CO10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[6]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5ED10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[7]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5ED20");
                else if(parent.getSelectedItem().toString().equals(bolgeler[8]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5GO10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[9]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5HA10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[10]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5IP10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[11]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5KE10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[12]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5KI10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[13]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5LU10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[14]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5MA20");
                else if(parent.getSelectedItem().toString().equals(bolgeler[15]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5SU20");
                else if(parent.getSelectedItem().toString().equals(bolgeler[16]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5TE10");
                else if(parent.getSelectedItem().toString().equals(bolgeler[17]))
                    wv.loadUrl("https://borsa.tobb.org.tr/fiyat_borsa.php?borsakod=5UZ10");





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
