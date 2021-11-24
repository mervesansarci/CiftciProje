package com.denemeler.projeciftci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mainToolbar;
    private BottomNavigationView bottomNavigationView;
    private Fragment tempFragment;
    private ViewPager2 viewPager2;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager2= findViewById(R.id.viewPager);
        SlideAdapter slideAdapter= new SlideAdapter(getSupportFragmentManager());
        slideAdapter.add(new ProfilFragment());
        slideAdapter.add(new FiyatFragment());
        slideAdapter.add(new HaberFragment());
        slideAdapter.add(new MesajFragment());

        //viewPager2.setAdapter(slideAdapter);
        //view page ekle
        viewPager2.setCurrentItem(0);
        viewPager2.setOffscreenPageLimit(1);

        bottomNavigationView= findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_tutucu,new ProfilFragment()).commit();

        mainToolbar= findViewById(R.id.mainToolbar);
        mainToolbar.setBackgroundColor(Color.parseColor("#288319"));

        mainToolbar.setTitleTextColor(Color.parseColor("#f8f8f8"));
        setSupportActionBar(mainToolbar);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.action_profil){
                    Toast.makeText(getApplicationContext(),"Birinci tıklandı",Toast.LENGTH_SHORT).show();
                    tempFragment= new ProfilFragment();
                    mainToolbar.setTitle("Profilim");


                }
                if(item.getItemId()==R.id.action_fiyatListe){
                    Toast.makeText(getApplicationContext(),"İkinci tıklandı",Toast.LENGTH_SHORT).show();
                    tempFragment= new FiyatFragment();
                    mainToolbar.setTitle("Fiyat Listesi");
                }
                if(item.getItemId()==R.id.action_haberler){
                    Toast.makeText(getApplicationContext(),"Üçüncü tıklandı",Toast.LENGTH_SHORT).show();
                    tempFragment= new HaberFragment();
                    mainToolbar.setTitle("Haberler");

                }

                if (item.getItemId()==R.id.action_mesaj){
                    Toast.makeText(getApplicationContext(),"Dördünce tıklandı",Toast.LENGTH_LONG).show();
                    tempFragment= new MesajFragment();
                    mainToolbar.setTitle("Mesaj Gönder");

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,tempFragment).commit();
                return true;

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser==null){

            sendToLogin();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:

                FirebaseAuth.getInstance().signOut();
                logOut();

                return true;

            case R.id.action_settings:
                Intent settingsIntent= new Intent(MainActivity.this,SetupActivity.class);
                startActivity(settingsIntent);

            default:
                return true;

        }

    }
    private void logOut() {


        sendToLogin();
    }

    private void sendToLogin() {
        Intent loginIntent= new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }


    private static class SlideAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        public SlideAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        void add(Fragment fragment){
            fragmentList.add(fragment);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}