package com.example.andropeinn

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class crear_cuenta : AppCompatActivity() {
    private lateinit var btnCrearCuenta:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)

        try{

            val mAuth=FirebaseAuth.getInstance()
            btnCrearCuenta=findViewById(R.id.crearCuenta)
            val edtUser=findViewById<EditText>(R.id.edt_usuario)
            val edtContrasenna=findViewById<EditText>(R.id.edtContra)

            btnCrearCuenta.setOnClickListener {
                mAuth.createUserWithEmailAndPassword(edtUser.text.toString(),edtContrasenna.text.toString())
                    .addOnCompleteListener{task->
                        if(task.isSuccessful){
                            val user:FirebaseUser?=mAuth.currentUser
                            Snackbar.make(it, "Usuario creado correctamente: ${user?.email}", Snackbar.LENGTH_SHORT).show()
                            //Toast.makeText(this, "Usuario creado exitosamente: ${user?.email}", Toast.LENGTH_SHORT).show()

                        }else{

                            val dialog=AlertDialog.Builder(this)
                                .setMessage(task.exception?.message)
                                .setPositiveButton("Aceptar"){dialog, it->}
                                .setNegativeButton("Cancelar"){dialog, it->}
                                .setCancelable(true)
                                .show()

                        }


                    }

            }


        }catch (e:Exception){
            val alertDialog=AlertDialog.Builder(this)
                .setMessage(e.message)
                .setPositiveButton("Aceptar"){dialog, it->}
                .setCancelable(true)
                .show()
        }

    }
}