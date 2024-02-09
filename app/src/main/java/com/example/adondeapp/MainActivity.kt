package com.example.adondeapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(StartFragment())

        // Verificar se a permissão já foi concedida
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val notificationHelper = NotificationHelper()
            notificationHelper.createNotification(
                context,
                "ADonde em execução",
                "Há produtos deliciosos perto de você, confira!"
            ) // 'this' é um contexto válido, geralmente referindo-se à atividade ou aplicativo
        } else {
            // Solicitar a permissão
            requestNotificationPermission()
        }
    }

    fun changeFragment(fragment: Fragment?) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_content, fragment!!)
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun requestNotificationPermission() {
        // Solicitar a permissão de notificação
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            NOTIFICATION_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            // Verificar se a permissão foi concedida
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, execute a lógica relacionada às notificações aqui
                val notificationHelper = NotificationHelper()
                notificationHelper.createNotification(
                    context,
                    "ADonde em execução",
                    "Há produtos deliciosos perto de você, confira!"
                ) // 'this' é um contexto válido, geralmente referindo-se à atividade ou aplicativo
            } else {
                // Explique ao usuário que a funcionalidade de notificação pode ser limitada sem a permissão
            }
        }
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_DENIED
        ) {
            val permission =
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            requestPermissions(permission, 112)
        }
    }

    fun changeActivity(view: View?) {
        if (PreferencesManager.getUsername(context) != null) {
            changeToMenuActvity()
        } else {
            changeToLoginActvity()
        }
    }

    fun changeToLoginActvity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun changeToMenuActvity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val NOTIFICATION_PERMISSION_CODE = 123
    }
}