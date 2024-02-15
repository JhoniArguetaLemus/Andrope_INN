package com.example.andropeinn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2

class la_taberna : AppCompatActivity() {
    lateinit var carouselAdapter: CarouselAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_la_taberna)

        try {


            val viewpager = findViewById<ViewPager2>(R.id.viewTaberna)

            val imagenes = listOf(
                R.drawable.taberna1,
                R.drawable.taberna2,
                R.drawable.taberna3,
                R.drawable.taberna4,
                R.drawable.taberna5
            )

            carouselAdapter = CarouselAdapter(this, imagenes)
            viewpager.adapter = carouselAdapter

            val btnReservar = findViewById<Button>(R.id.btnReservarTaberna)
             btnReservar.setOnClickListener {
                val intent = Intent(this, reserva::class.java)
                intent.putExtra("nombre_local", "La taberna del viejo faro")
                startActivity(intent)
            }



        }catch (e:Exception){
            val alertDialog=AlertDialog.Builder(this)
                .setMessage(e.message)
                .setPositiveButton("Aceptar"){dialog, it->}
                .show()
        }
    }
}