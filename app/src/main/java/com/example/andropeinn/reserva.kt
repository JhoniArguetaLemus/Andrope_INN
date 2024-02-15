package com.example.andropeinn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class reserva : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        val bundle=intent.extras

        val local=bundle?.getString("nombre_local")

        val nombre_local=findViewById<TextView>(R.id.nombre_local)
        nombre_local.setText(local)






    }
}