package com.example.adondeapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

object PreferencesManager {
    private const val PREF_NAME = "MyAppPreferences"
    private const val KEY_USER_LOGGED_IN = "user_logged_in"
    private const val KEY_USER_ID = "user_id"
    private const val USERNAME_LOG_IN = "username"
    private const val USER_PHOTO_KEY = "user_photo"
    fun setUserLoggedIn(context: Context, isLoggedIn: Boolean) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_USER_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    fun setUsername(context: Context, username: String?) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(USERNAME_LOG_IN, username)
        editor.apply()
    }

    fun setUserPhoto(context: Context, photo: Bitmap?) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        // Converte o Bitmap para uma representação String usando Base64
        val encodedPhoto = encodeBitmapToString(photo)
        editor.putString(USER_PHOTO_KEY, encodedPhoto)
        editor.apply()
    }

    fun setUserPhotoString(context: Context?, photo: String?) {
        val editor = context?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)?.edit()
        // Converte o Bitmap para uma representação String usando Base64
        editor!!.putString(USER_PHOTO_KEY, photo)
        editor.apply()
    }

    fun setUserPhotoDelete(context: Context) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        // Remove a entrada da imagem
        editor.remove(USER_PHOTO_KEY)
        editor.apply()
    }

    fun getUserPhoto(context: Context?): Bitmap? {
        val preferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val encodedPhoto = preferences.getString(USER_PHOTO_KEY, null)
        return if (encodedPhoto != null) {
            // Converte a String Base64 de volta para Bitmap
            decodeStringToBitmap(encodedPhoto)
        } else {
            null
        }
    }

    fun getUserPhotoString(context: Context?): String? {
        val preferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val encodedPhoto = preferences.getString(USER_PHOTO_KEY, null)
        return if (encodedPhoto != null) {
            encodedPhoto
        } else {
            null
        }
    }

    fun isUserLoggedIn(context: Context): Boolean {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(KEY_USER_LOGGED_IN, false)
    }

    fun setUserId(context: Context, userId: String?) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(KEY_USER_ID, userId)
        editor.apply()
    }

    fun getUserId(context: Context): String? {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return preferences.getString(KEY_USER_ID, null)
    }

    fun getUsername(context: Context?): String? {
        val preferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return preferences.getString(USERNAME_LOG_IN, null)
    }

    fun encodeBitmapToString(bitmap: Bitmap?): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    // Método auxiliar para converter String Base64 para Bitmap
    fun decodeStringToBitmap(encodedString: String): Bitmap? {
        val decodedBytes = Base64.decode(encodedString, Base64.DEFAULT)

        // Verifique se decodedBytes não é nulo antes de criar o Bitmap
        return if (decodedBytes != null) {
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } else {
            null
        }
    }
}
