package com.example.adondeapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_PERMISSION_CODE = 123;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(new StartFragment());

        // Verificar se a permissão já foi concedida
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationHelper notificationHelper = new NotificationHelper();
            notificationHelper.createNotification(context, "ADonde em execução", "Há produtos deliciosos perto de você, confira!");  // 'this' é um contexto válido, geralmente referindo-se à atividade ou aplicativo

        } else {
            // Solicitar a permissão
            requestNotificationPermission();
        }

    }

    public void changeFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void requestNotificationPermission() {
        // Solicitar a permissão de notificação
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            // Verificar se a permissão foi concedida
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, execute a lógica relacionada às notificações aqui
                NotificationHelper notificationHelper = new NotificationHelper();
                notificationHelper.createNotification(context, "ADonde em execução", "Há produtos deliciosos perto de você, confira!");  // 'this' é um contexto válido, geralmente referindo-se à atividade ou aplicativo
            } else {
                // Explique ao usuário que a funcionalidade de notificação pode ser limitada sem a permissão
            }
        }

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permission, 112);
        }
    }

    public void changeActivity(View view){
        if(PreferencesManager.getUsername(context)!=null){
         changeToMenuActvity();
        }else {
            changeToLoginActvity();
        }
    }

    public void changeToLoginActvity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void changeToMenuActvity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

}