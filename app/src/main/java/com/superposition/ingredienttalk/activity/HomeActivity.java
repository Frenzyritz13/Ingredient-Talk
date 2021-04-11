package com.superposition.ingredienttalk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.superposition.ingredienttalk.R;
import com.superposition.ingredienttalk.fragment.ProfileFragment;
import com.superposition.ingredienttalk.fragment.ReceipeFragment;

public class HomeActivity extends AppCompatActivity {

    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        openProfile();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.nav_profile){
                    openProfile();
                }else {
                    openReceipe();
                }

                return true;
            }
        });
    }

    private void openProfile(){
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .replace(R.id.frameLayoutAdmin, new ProfileFragment()).commit();
    }

    private void openReceipe(){
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .replace(R.id.frameLayoutAdmin, new ReceipeFragment()).commit();
    }
}