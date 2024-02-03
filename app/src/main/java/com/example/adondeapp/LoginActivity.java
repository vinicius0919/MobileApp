package com.example.adondeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Context context = this;
    Switch switcher = new Switch(getSupportFragmentManager());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        switcher.changeFragment(new LoginFragment());
    }

    public void clickListener(View view) {

        EditText el = findViewById(R.id.textInput);
        EditText el2 = findViewById(R.id.passwordInput);

        String username = el.getText().toString().trim();
        String password = el2.getText().toString().trim();

        TextView alertUsr = findViewById(R.id.alertaUsrname);
        TextView alertPass = findViewById(R.id.alertaPassword);


        // Chame o método signIn

        if (!TextUtils.isEmpty(username)) {

            if (!TextUtils.isEmpty((password))) {
                alertPass.setVisibility(View.INVISIBLE);
                alertUsr.setVisibility(View.INVISIBLE);

                // Após o login bem-sucedido
                PreferencesManager.setUserLoggedIn(context, true);
                PreferencesManager.setUserId(context, "123456");
                PreferencesManager.setUsername(context, username);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            } else {
                alertPass.setVisibility(View.VISIBLE);
            }
        } else {
            alertUsr.setVisibility(View.VISIBLE);
        }


    }
}