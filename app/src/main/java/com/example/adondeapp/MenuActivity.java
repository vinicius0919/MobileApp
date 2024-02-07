package com.example.adondeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MenuActivity extends AppCompatActivity {
    Context context = this;
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        LinearLayout linearLayout1 =(LinearLayout) findViewById(R.id.opcao1);
        LinearLayout linearLayout4 = (LinearLayout)findViewById(R.id.opcao4);
        LinearLayout linearLayout5 = (LinearLayout)findViewById(R.id.opcao5);
        LinearLayout linearLayout7 = (LinearLayout)findViewById(R.id.opcao7);


        linearLayout1.setOnClickListener(v -> changeToMapsActvity());
        linearLayout4.setOnClickListener(v -> changeToCadastroActvity());
        linearLayout5.setOnClickListener(v -> changeToLoginActvity());
        linearLayout7.setOnClickListener(v -> {changeToPerfilFragment();});

    }

    public void changeToMapsActvity() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
    public void changeToCadastroActvity() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
    public void changeToLoginActvity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void changeToPerfilFragment() {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.menutela,new MyAccountFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
