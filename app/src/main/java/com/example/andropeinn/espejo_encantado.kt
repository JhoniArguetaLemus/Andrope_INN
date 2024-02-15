package com.example.andropeinn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2

class espejo_encantado : AppCompatActivity() {
    private lateinit var carouselAdapter: CarouselAdapter
    lateinit var btnReservar:Button
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_espejo_encantado)

        val viewPager=findViewById<ViewPager2>(R.id.viewPager)

        val images= listOf(
            R.drawable.espejo_encantado1,
            R.drawable.espejo_encantado2,
            R.drawable.espejo_encantado3,
            R.drawable.espejo_encantado4
        )



        carouselAdapter=CarouselAdapter(this, images)

        viewPager.adapter=carouselAdapter


        //boton de reservas

        btnReservar=findViewById<Button?>(R.id.btnReservar)

        btnReservar.setOnClickListener{
          val intent=  Intent(this, reserva::class.java)
            intent.putExtra("nombre_local", "La casa del espejo encantado")
            startActivity(intent)


        }
    }
}