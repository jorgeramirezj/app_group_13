package com.mintic.ciclo4.appgroup13

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var edtUsername : EditText
    private lateinit var edtPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.tbMyToolbar))
        edtUsername = findViewById(R.id.etUsername)
        edtPassword = findViewById(R.id.etPassword)
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
                val intento = Intent(this, ToDoActivity::class.java)
                startActivity(intento)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun onLogin(view : View){
        // Acceder a la informacion de los objetos
        var username : String = edtUsername.text.toString()
        var password : String = edtPassword.text.toString()

        // Hacer clic en boton OK, ACEPTAR
        var opcionPositivo = {
            dialogo : DialogInterface, which : Int ->
            val intento = Intent(this, WelcomeActivity::class.java)
            intento.putExtra("username", username)
            startActivity(intento)
        }

        // funcion de tipo lambda para hacer clic en boton NO, CANCELAR
        val opcionNegativo = { _: DialogInterface, _: Int -> }

        if(username.equals("jr") && password.equals("1234")){
            val dialogo = AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.txt_WELCOME))
                .setMessage(resources.getString(R.string.txt_User_two_points) + username)
                .setPositiveButton(resources.getString(R.string.txt_OK), opcionPositivo)
                .setNegativeButton(resources.getString(R.string.txt_CANCEL), opcionNegativo)
                .create()
                .show()
        } else {
            /*val dialogo = AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.MainActivity_txtErrLoginTitle))
                .setMessage(resources.getString(R.string.MainActivity_txtErrLoginText))
                .create()
                .show()*/
            Toast.makeText(this, resources.getString(R.string.txt_invalid_login), Toast.LENGTH_SHORT).show()
        }

    }
}