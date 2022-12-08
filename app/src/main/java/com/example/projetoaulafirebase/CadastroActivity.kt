package com.example.projetoaulafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.projetoaulafirebase.databinding.ActivityCadastroBinding
import com.example.projetoaulafirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {
    private val TAG = "info_firebase"

    private  val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       binding.btnCadastrar.setOnClickListener {
           cadastrarUsuario()
       }

    }
    private fun cadastrarUsuario(){

        val email = binding.editEmailCadastro.text.toString()
        val senha = binding.editSenhaCadastro.text.toString()
        auth.createUserWithEmailAndPassword(
            email,senha
        ).addOnSuccessListener { authResult ->
            Toast.makeText(this, "Sucesso ao cadastrar usuario", Toast.LENGTH_SHORT).show()


            val intent = Intent(this,PrincipalActivity::class.java)
            startActivity(intent)

        }.addOnFailureListener{exception ->
            Toast.makeText(this, "Erro ao cadastrar usuario", Toast.LENGTH_SHORT).show()


        }

    }
}