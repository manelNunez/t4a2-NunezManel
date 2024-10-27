package com.example.t4a2_nunezmanel

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t4a2_nunezmanel.databinding.ActivityCambiarContrasenaBinding
import kotlin.system.exitProcess

class CambiarContrasena : AppCompatActivity() {

    private lateinit var binding: ActivityCambiarContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        binding = ActivityCambiarContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botonCambiar = binding.canviarBtn
        val botonVolver = binding.volverBtn
        val usuario = intent.getStringExtra("Usuario")
        val password = intent.getStringExtra("Password")

        botonCambiar.setOnClickListener {

            val passwordActual = binding.contrasenaActualTiet
            val passwordNueva = binding.contrasenaNuevaTiet
            var enviar = true

            if(passwordActual.text.toString() == passwordNueva.text.toString()){
                binding.contrasenaNuevaTil.error = "La contraseña nueva no puede ser igual a la anterior"
                binding.contrasenaNuevaTil.errorIconDrawable = null
                enviar = false
            }
            else
                binding.contrasenaNuevaTil.error = null

            if(passwordActual.text.toString() != password){
                binding.contrasenaActualTil.error = "Contraseña incorrecta"
                binding.contrasenaActualTil.errorIconDrawable = null
                enviar = false
            }
            else
                binding.contrasenaActualTil.error = null

            if (!passwordNueva.text.toString().matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$"))){
                binding.contrasenaNuevaTil.error = "La contraseña debe tener al menos 8 caracteres, con 1 minúscula, 1 mayúscula, 1 número y 1 símbolo"
                binding.contrasenaNuevaTil.errorIconDrawable = null
                enviar = false
            }
            else
                binding.contrasenaNuevaTil.error = null



            if (enviar == true){
                binding.contrasenaNuevaTil.error = null
                binding.contrasenaActualTil.error = null
                val intent:Intent = Intent(this, MainActivity::class.java)

                intent.putExtra("Password",passwordNueva.text)
                intent.putExtra("Usuario",usuario)
                startActivity(intent)
            }


        }

        botonVolver.setOnClickListener {

            exitProcess(0);

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