package com.example.projetoaulafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projetoaulafirebase.databinding.ActivityMainBinding
import com.example.projetoaulafirebase.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PrincipalActivity : AppCompatActivity() {
    private  val binding by lazy {
        ActivityPrincipalBinding.inflate(layoutInflater)
    }
    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onStart() {
        super.onStart()

        firestore
            .collection("tarefa")
            // .whereEqualTo("tarefa", "alugar carro") significa onde é igual.
            // .whereNotEqualTo("tarefa", "alugar carro") significa onde não é igual.
            // .whereNotEqualTo("tarefa", "alugar carro") significa onde não é igual.
            // .whereIn("tarefa", "alugar carro") significa onde está dentro de.
            // .whereNotIn("tarefa", "alugar carro") significa onde não está dentro de.
            // .whereArrayContains("tarefa", "alugar carro") significa onde não é igual.
            //.whereGreaterThan("idade", "20") significa maior que
            //.whereGreaterThanOrEqualTo("idade", "20") significa maior ou igual que
            //.whereLessThan("idade", "20") significa menor que
            //.whereLessThanOrEqualTo("idade", "20")significa menor ou igual que


            /*//idade maior ou igual a 20 E menor ou igual a 40
            .whereGreaterThanOrEqualTo("idade", "20" )
            .whereLessThanOrEqualTo("idade", "40" )*/

           /* //ordena do maior para o menor
            .whereGreaterThan("idade", "20" )
            .orderBy("idade", Query.Direction.DESCENDING )*/

             /*//ordena do menor para o maior
            .whereGreaterThan("idade", "20" )
            .orderBy("idade", Query.Direction.ASCENDING )*/


            .addSnapshotListener { querySnapshot, error ->
                val documentos = querySnapshot?.documents

                var texto = ""
                documentos?.forEach {DocumentSnapshot ->
                    val dados = DocumentSnapshot.data
                    val id = DocumentSnapshot.id
                    if (dados!= null){
                        val tarefa = dados["tarefa"]
                        val tags = dados["tags"]

                        val nome = dados["nome"]
                        val idade = dados["idade"]


                        texto += "  $tarefa - ${tags.toString()} \n \n"
                      /*  texto += " $nome - $idade  \n \n"*/
                    }

                }
                binding.textTarefas.text = texto
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCadastrarTarefa.setOnClickListener {
            val tarefa = binding.editTarefa.text.toString()

            val dados = mapOf(


                "tarefa" to tarefa,
                "tags" to listOf("tarefa")
            )
            firestore
                .collection( "tarefa")
                .add(dados)
                .addOnSuccessListener {
                    Toast.makeText(this, "Sucesso ao salvar a tarefa", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this, "Erro ao salvar a tarefa", Toast.LENGTH_SHORT).show()
                }
        }
    }
}