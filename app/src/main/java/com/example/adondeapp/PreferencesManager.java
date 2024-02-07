package com.example.adondeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class PreferencesManager {
    private static final String PREF_NAME = "MyAppPreferences";
    private static final String KEY_USER_LOGGED_IN = "user_logged_in";
    private static final String KEY_USER_ID = "user_id";
    private static final String USERNAME_LOG_IN = "username";
    private static final String USER_PHOTO_KEY = "user_photo";

    public static void setUserLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_USER_LOGGED_IN, isLoggedIn);
        editor.apply();
    }
    public static void setUsername(Context context, String username) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(USERNAME_LOG_IN, username);
        editor.apply();
    }

    public static void setUserPhoto(Context context, Bitmap photo) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        // Converte o Bitmap para uma representação String usando Base64
        String encodedPhoto = encodeBitmapToString(photo);
        editor.putString(USER_PHOTO_KEY, encodedPhoto);
        editor.apply();
    }

    public static Bitmap getUserPhoto(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String encodedPhoto = preferences.getString(USER_PHOTO_KEY, null);

        if (encodedPhoto != null) {
            // Converte a String Base64 de volta para Bitmap
            return decodeStringToBitmap(encodedPhoto);
        } else {
            return null;
        }
    }

    public static boolean isUserLoggedIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_USER_LOGGED_IN, false);
    }

    public static void setUserId(Context context, String userId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_USER_ID, userId);
        editor.apply();
    }

    public static String getUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USER_ID, null);
    }

    public static String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(USERNAME_LOG_IN, null);
    }





    private static String encodeBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    // Método auxiliar para converter String Base64 para Bitmap
    private static Bitmap decodeStringToBitmap(String encodedString) {
        byte[] decodedBytes = Base64.decode(encodedString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
