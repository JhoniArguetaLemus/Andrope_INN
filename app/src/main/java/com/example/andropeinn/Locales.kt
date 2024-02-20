package com.example.andropeinn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.andropeinn.databinding.ActivityLocalesBinding
import kotlinx.coroutines.flow.combineTransform

class Locales : AppCompatActivity() {
    private lateinit var binding:ActivityLocalesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLocalesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //obtener el usuario de la persona que inicio sesion

        val extra=intent.extras
        val usuario=extra?.getString("usuario", "")
        val txtUsuario=findViewById<TextView>(R.id.txtUsuario)
        txtUsuario.setText("Bienvenido: ${usuario}")


        //botones que llevan a las activies donde se muestran los detalles de cada local
        binding.btnJardin.setOnClickListener{ cambiarActivity(el_jardin()) }

        binding.btnEspejo.setOnClickListener{ cambiarActivity(espejo_encantado()) }

        binding.btnVerTaberna.setOnClickListener{ cambiarActivity(la_taberna()) }

        binding.btnEstrellas.setOnClickListener{ cambiarActivity(las_estrellas()) }


    }

    private fun cambiarActivity(activity:Activity){
        startActivity(Intent(this,activity::class.java ))
    }
}