package com.example.adondeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void clickListenner(View view){
        TextView txtM = findViewById(R.id.textMessage);
        EditText el = findViewById(R.id.textInput);
        EditText el2 = findViewById(R.id.passwordInput);

        String username =el.getText().toString().trim();
        String password =el2.getText().toString().trim();


        if(!TextUtils.isEmpty(username)){

            if(!TextUtils.isEmpty((password))){
                map.put("username",username);
                map.put("password",password);
                txtM.setText("Usuário Cadastrado com sucesso!");
            }else{
                txtM.setText("Password Inválido");
            }
        }else{
            txtM.setText("Username Inválido");
        }

    }

}