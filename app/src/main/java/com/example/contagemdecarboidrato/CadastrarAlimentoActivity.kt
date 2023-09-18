package com.example.contagemdecarboidrato

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contagemdecarboidrato.databinding.ActivityCadastrarAlimentoBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastrarAlimentoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastrarAlimentoBinding
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarAlimentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)!!

        binding.btCadastrar.setOnClickListener {

            val nomeAlimento = binding.etCadastrarNome.text.toString().uppercase()
            val carboPorGrama = binding.etCadastrarCarboPorGrama.text.toString()

            if (nomeAlimento == "" || carboPorGrama == "") {

                Snackbar.make(it, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.RED).show()
            }

            if (nomeAlimento.isNotEmpty() && carboPorGrama.isNotEmpty()) {
                CoroutineScope(Dispatchers.Main).launch {
                    db.alimentoDao().insert(Alimento(0, nomeAlimento, carboPorGrama))

                    Toast.makeText(
                        applicationContext,
                        "Alimento criado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                //usei a intent combinado com finish para quando retornar à tela principal já
                // renove a activity e apereça o alimento recém cadastrado no autocomplete
                val intent = Intent(this, TelaPrincipalActivity::class.java)
                finish()
                startActivity(intent)

            }

        }

    }

}