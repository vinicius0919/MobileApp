package com.example.adondeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<String, String> map = new HashMap<String, String>();
    private boolean logged = false;

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(false);
    }

    public void changeFragment(boolean boo) {
        setLogged(boo);
        FragmentManager fm = getSupportFragmentManager();

        // Abre uma transação e adiciona
        FragmentTransaction ft = fm.beginTransaction();

        if (!isLogged()) {
            ft.replace(R.id.fragment_content, new StartFragment());
        } else {
            ft.replace(R.id.fragment_content, new MenuFragment());
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    public void clickListener(View view) {

        EditText el = findViewById(R.id.textInput);
        EditText el2 = findViewById(R.id.passwordInput);

        String username = el.getText().toString().trim();
        String password = el2.getText().toString().trim();

        TextView alertUsr = findViewById(R.id.alertaUsrname);
        TextView alertPass = findViewById(R.id.alertaPassword);
        if (!TextUtils.isEmpty(username)) {

            if (!TextUtils.isEmpty((password))) {

                alertPass.setVisibility(View.INVISIBLE);
                alertUsr.setVisibility(View.INVISIBLE);
                changeFragment(true);
            } else {
                alertPass.setVisibility(View.VISIBLE);
            }
        } else {
            alertUsr.setVisibility(View.VISIBLE);
        }


    }

    public void startTutorial(View view) {
        FragmentManager fm = getSupportFragmentManager();

        // Abre uma transação e adiciona
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_content, new LoginFragment());
        ft.addToBackStack(null);

        ft.commit();
    }

    public void changeFragment3(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}