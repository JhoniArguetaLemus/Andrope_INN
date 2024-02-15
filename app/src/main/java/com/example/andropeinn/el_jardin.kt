package com.example.andropeinn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2

class el_jardin : AppCompatActivity() {
    private lateinit var viewPager:ViewPager2
    private lateinit var carouselAdapter: CarouselAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_el_jardin)

        viewPager=findViewById(R.id.viewPager)

        val images= listOf(
            R.drawable.jardin1,
            R.drawable.jardin2,
            R.drawable.jardin3,
            R.drawable.jardin4,
            R.drawable.jardin5

        )


        carouselAdapter= CarouselAdapter(this, images)
        viewPager.adapter=carouselAdapter


        val btnReservar=findViewById<Button>(R.id.btnReservarJardin)
        btnReservar.setOnClickListener{
            val intent=  Intent(this, reserva::class.java)
            intent.putExtra("nombre_local", "El jardin de las mariposas")
            startActivity(intent)
        }
    }
}