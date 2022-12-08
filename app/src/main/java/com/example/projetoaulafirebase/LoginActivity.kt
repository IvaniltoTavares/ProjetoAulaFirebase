package com.example.projetoaulafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.projetoaulafirebase.databinding.ActivityCadastroBinding
import com.example.projetoaulafirebase.databinding.ActivityLoginBinding
import com.example.projetoaulafirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val TAG = "info_firebase"
    private  val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onStart(){
        super.onStart()
            /*auth.signOut()*/
        val usuarioAtual = auth.currentUser
        if (usuarioAtual != null){
            val intent = Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCadastro.setOnClickListener{
           telaCadastro()
        }
        binding.btnLogin.setOnClickListener{
            logarUsuario()
        }

    }
    private fun logarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if(email.isEmpty() or  senha.isEmpty()){
            Toast.makeText(this, "Erro ao logar usuario ou Usuário não cadastrado", Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(
                email,senha
            ).addOnSuccessListener { authResult ->
                Toast.makeText(this, "Sucesso ao logar usuario", Toast.LENGTH_SHORT).show()


                val intent = Intent(this,PrincipalActivity::class.java)
                startActivity(intent)

            }.addOnFailureListener{exception ->
                Toast.makeText(this, "Erro ao logar usuario ou Usuário não cadastrado", Toast.LENGTH_SHORT).show()


            }
        }


    }
    private fun telaCadastro(){

        val intent = Intent(this,CadastroActivity::class.java)
        startActivity(intent)

    }

}