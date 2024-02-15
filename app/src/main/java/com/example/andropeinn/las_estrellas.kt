package com.example.andropeinn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2

class las_estrellas : AppCompatActivity() {
    private lateinit var btnReservar:Button
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var viewPager: ViewPager2
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_las_estrellas)

        viewPager=findViewById(R.id.viewPager)

        val imagenes= listOf(
            R.drawable.estrellas1,
            R.drawable.estrellas2,
            R.drawable.estrellas3
        )

        carouselAdapter= CarouselAdapter(this, imagenes)
        viewPager.adapter=carouselAdapter

        btnReservar=findViewById(R.id.btnReservar)
        btnReservar.setOnClickListener {
            reservar()
        }
       
        

    }

    private fun reservar(){

        val intent=  Intent(this, reserva::class.java)
        intent.putExtra("nombre_local", "El salon de las estrellas fugaces")
        startActivity(intent)

    }


}