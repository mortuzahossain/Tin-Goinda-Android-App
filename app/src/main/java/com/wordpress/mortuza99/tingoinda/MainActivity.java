package com.wordpress.mortuza99.tingoinda;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.wordpress.mortuza99.tingoinda.Fragments.Home;
import com.wordpress.mortuza99.tingoinda.Fragments.Library;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavigationView;
    private FrameLayout mMaineFrame;

    private Home homeFragment;
    private Library libraryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMaineFrame = findViewById(R.id.mainframe);
        mNavigationView = findViewById(R.id.navbottom);

        homeFragment = new Home();
        libraryFragment = new Library();

        setFragment(homeFragment);

        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navhome:
                        setFragment(homeFragment);
                        return true;
                    case R.id.navlibrary:
                        setFragment(libraryFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe, fragment);
        fragmentTransaction.commit();
    }
}
