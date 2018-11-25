package com.example.myeonghusong.lightinstagram;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class HomeActivity extends AppCompatActivity {

    private final int CODE_UPLOAD = 2;

    private BottomNavigationView bottomNavigationView;

//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_home;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeFragment();

        findView();

        setBottomNavigationView();




    }

    public void initializeFragment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contents, new HomeFragment());
        fragmentTransaction.commit();
    }

    public void findView()
    {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
    }

    public void setBottomNavigationView()
    {

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.bottombar_home:



                                return true;

                            case R.id.bottombar_search:



                                return true;

                            case R.id.bottombar_upload:

                                onButtonClicked_Upload();

                                return true;

                            case R.id.bottombar_like:


                                return true;

                            case R.id.bottombar_user:


                                return true;
                        }
                        return false;
                    }
                });
    }

    public void onButtonClicked_Upload()
    {

        Intent intent3 = new Intent(HomeActivity.this, UploadActivity.class);
        startActivity(intent3);

    }



}
