package com.mintic.ciclo4.appgroup13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class WelcomeActivity : AppCompatActivity() {

    private lateinit var txtUsername : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setSupportActionBar(findViewById(R.id.tbMyToolbar))
        txtUsername = findViewById(R.id.tvUsername)
        txtUsername.setText(intent.getStringExtra("username"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.accion_buscar -> {
                Toast.makeText(this,
                    R.string.appbar_txt_action_Search,
                    Toast.LENGTH_LONG
                ).show()
                return true
            }
            R.id.accion_configurar -> {
                Toast.makeText(this,
                    R.string.appbar_txt_action_Settings,
                    Toast.LENGTH_LONG
                ).show()
                return true
            }
            R.id.accion_cerrar_sesion -> {
                Toast.makeText(this,
                    R.string.appbar_txt_action_Logout,
                    Toast.LENGTH_LONG
                ).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}