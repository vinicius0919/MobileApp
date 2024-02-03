package com.example.adondeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class MainActivity extends AppCompatActivity {

    private Context context = this;
    Switch switcher = new Switch(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(new StartFragment());
    }
    public void changeFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }




    public void changeToMapsActvity(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

}