package com.example.andropeinn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    private lateinit var googleApiClient:GoogleApiClient
    private val RC_SIGN_IN = 9001
    private lateinit var edtUsuario:EditText
    private lateinit var edtContrasenna:EditText
    private lateinit var btnLogin:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sesionGoogle()


        edtUsuario=findViewById(R.id.edt_usuario)
       // edtUsuario.doOnTextChanged{text, start, before, count->manageButtonLogin() }


        edtContrasenna=findViewById<EditText>(R.id.edt_contrasenna)
        //edtContrasenna.doOnTextChanged{text, start,before, count->manageButtonLogin()}

        val mAuth=FirebaseAuth.getInstance()

        btnLogin=findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {

            //comprobar que los campos no esten vacios
            if(!edtUsuario.text.isNullOrEmpty() && !edtContrasenna.text.isNullOrEmpty()){
                mAuth.signInWithEmailAndPassword(edtUsuario.text.toString(), edtContrasenna.text.toString())
                    .addOnCompleteListener {task->
                        if(task.isSuccessful){

                            val intent=Intent(this, Locales::class.java)
                            intent.putExtra("usuario", edtUsuario.text.toString())
                            startActivity(intent)
                        }else{
                            Snackbar.make(it, "Error: ${task.exception?.message}", Snackbar.LENGTH_SHORT).show()
                        }

                    }



            }else{
                Snackbar.make(it, "Rellene todos los campos", Snackbar.LENGTH_SHORT).show()
            }

        }

        val btnGoogle=findViewById<Button>(R.id.btnLoginGoogle)
        btnGoogle.setOnClickListener {
            val singInIntent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(singInIntent, RC_SIGN_IN)

        }


        val txtCrearCuenta=findViewById<TextView>(R.id.txtCrearCuenta)
        txtCrearCuenta.setOnClickListener{
            startActivity(Intent(this, crear_cuenta::class.java))
        }


    }

    private fun manageButtonLogin() {


        if(TextUtils.isEmpty(edtContrasenna.text.toString()) || !validarEmail.isEmail(edtUsuario.text.toString())){
            btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.grisClaro))
            btnLogin.isEnabled=false
        }else{
            btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.grisOscuro))
            btnLogin.isEnabled=true
        }
    }


    private  fun sesionGoogle(){

        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleApiClient=GoogleApiClient.Builder(this)
            .enableAutoManage(this){connectionResult->
                Log.d("GoogleSignIn", "Error de conexion"+connectionResult)

            }
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result=Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)

        if(result!!.isSuccess){


            val intent=Intent(this@Login, Locales::class.java)

            startActivity(intent)

            finish()
        }else{
            Log.e("GoogleSingIn", "Inicio de sesion con google fallo")
        }

    }
}