package com.example.t4a2_nunezmanel

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t4a2_nunezmanel.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val entrar = binding.enviarBtn
        val salir = binding.salirBtn

        entrar.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            val usuario = binding.usuarioTiet
            val password = binding.passwordTiet
            var validation = true


            if (usuario.text.toString().length == 9) {
                if (usuario.text.toString().matches(Regex("\\d{8}[a-zA-Z]?"))) {
                    binding.usuarioTil.error = null
                } else {
                    binding.usuarioTil.error = "Introduce un DNI correcto de 8 digitos y una letra"
                    validation = false
                }
            } else {
                binding.usuarioTil.error = "Introduce un DNI correcto"
                validation = false
            }
            if (password.text.toString().length > 8) {
                if (password.text.toString().matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$")))
                    binding.passwordTil.error = null
                else {
                    binding.passwordTil.error = "La contraseña debe tener al menos 8 caracteres, con 1 minúscula, 1 mayúscula, 1 número y 1 símbolo"
                    binding.passwordTil.errorIconDrawable = null
                    validation = false
                }
            } else {
                binding.passwordTil.error = "Introduce una contraseña correcta de 8 o mas caracteres"
                binding.passwordTil.errorIconDrawable = null
                validation = false
            }
            binding.passwordTil.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE

            if (validation) {
                intent.putExtra("Usuario", usuario.text.toString())
                intent.putExtra("Password", password.text.toString())
                startActivity(intent)
            }
        }

        salir.setOnClickListener{

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