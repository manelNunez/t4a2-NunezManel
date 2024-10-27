package com.example.t4a2_nunezmanel

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t4a2_nunezmanel.databinding.ActivityLoginBinding
import com.example.t4a2_nunezmanel.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuario_tv = binding.usuarioInfoTv

        usuario_tv.text = intent.getStringExtra("Usuario")

        val password = intent.getStringExtra("Password")

        val btn_salir = binding.salir
        val btn_cambiarContrasena = binding.cambiarContrasena

        btn_salir.setOnClickListener {
            exitProcess(0)
        }

        btn_cambiarContrasena.setOnClickListener{

            val intent: Intent = Intent(this,CambiarContrasena::class.java)
            intent.putExtra("Password",password)
            intent.putExtra("Usuario",usuario_tv.text)

            startActivity(intent)
        }


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}