package com.example.adondeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Context context = this;
    Switch switcher = new Switch(getSupportFragmentManager());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnMenu = findViewById(R.id.voltarMenu);
        Button btnLogar = findViewById(R.id.logar01);
        btnLogar.setOnClickListener(v->{
            clickListener();
        });


        ImageView minhafoto = (ImageView) findViewById(R.id.minhafotodeperfil);
        if(PreferencesManager.getUsername(context)!=null){
            int black = Color.parseColor("#000000");
            btnMenu.setClickable(true);
            btnMenu.setBackgroundColor(black);
            btnMenu.setVisibility(View.VISIBLE);
            btnMenu.setOnClickListener(v->{
                Intent intent = new Intent(context, MenuActivity.class);
                startActivity(intent);
            });
        }
        if(PreferencesManager.getUserPhoto(context)!=null){
            minhafoto.setImageBitmap(PreferencesManager.getUserPhoto(context));
        }
    }

    public void switchCadastro(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void clickListener() {

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

                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);

            } else {
                alertPass.setVisibility(View.VISIBLE);
            }
        } else {
            alertUsr.setVisibility(View.VISIBLE);
        }


    }
}