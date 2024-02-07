package com.example.adondeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {
Context context =this;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView click_image_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        click_image_id = findViewById(R.id.imageFoto);
        click_image_id.setOnClickListener(v -> {
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE);
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            // BitMap is data structure of image file which store the image in memory
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            PreferencesManager.setUserPhoto(context,photo);
            // Set the image in imageview for display
            click_image_id.setImageBitmap(photo);
            click_image_id.setBackground(null);
        }
    }
    private void openCamera() {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE);
    }

    public void switchLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
