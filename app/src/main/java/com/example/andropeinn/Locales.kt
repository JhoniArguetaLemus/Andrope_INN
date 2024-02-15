package com.example.andropeinn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.btnJardin.setOnClickListener{
            cambiarActivity(el_jardin())
           // startActivity(Intent(this, el_jardin::class.java))
        }


        binding.btnEspejo.setOnClickListener{
            cambiarActivity(espejo_encantado())
           // startActivity(Intent(this, espejo_encantado::class.java))
        }

        binding.btnVerTaberna.setOnClickListener{

            try {
                cambiarActivity(la_taberna())
               // startActivity(Intent(this, la_taberna::class.java))
            }catch (e:Exception){
                val alertDialog=AlertDialog.Builder(this)
                    .setMessage(e.message)
                    .setPositiveButton("Aceptar"){dialog, it->}
                    .show()
            }

        }

        binding.btnEstrellas.setOnClickListener{
            cambiarActivity(las_estrellas())
        }


    }

    private fun cambiarActivity(activity:Activity){
        startActivity(Intent(this,activity::class.java ))
    }
}