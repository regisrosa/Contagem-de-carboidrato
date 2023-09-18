package com.example.contagemdecarboidrato

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.contagemdecarboidrato.databinding.ActivityCorrigirBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CorrigirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCorrigirBinding
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCorrigirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)!!

        var ac_buscar_nome = binding.acBuscarNome
        var etCorrigirNome = binding.etCorrigirNome
        etCorrigirNome.isVisible = false
        var etCorrigirCarbo = binding.etCorrigirCarbo
        etCorrigirCarbo.isVisible = false
        var bt_buscar = binding.btBuscar
        var bt_corrigir = binding.btCorrigir
        bt_corrigir.isVisible = false


        CoroutineScope(Dispatchers.Main).launch {
            var listaAlimentos = db.alimentoDao().getAll()
            val adapter =
                ArrayAdapter(applicationContext, R.layout.select_dialog_item, listaAlimentos)
            ac_buscar_nome.threshold = 2
            ac_buscar_nome.setAdapter(adapter)

        }


        bt_buscar.setOnClickListener {
            var nomeAlimento = ac_buscar_nome.text.toString()
            if (nomeAlimento != "") {
                CoroutineScope(Dispatchers.Main).launch {
                    var id = db.alimentoDao().getId(nomeAlimento)
                    var valorCarbo = db.alimentoDao().carboPorGrama(nomeAlimento)

                    etCorrigirNome.isVisible = true
                    etCorrigirNome.setText(nomeAlimento)
                    etCorrigirCarbo.isVisible = true
                    etCorrigirCarbo.setText(valorCarbo)
                    bt_corrigir.isVisible = true

                    bt_corrigir.setOnClickListener {
                        var nomeCorrigido = etCorrigirNome.text.toString().uppercase()
                        var carboCorrigido = etCorrigirCarbo.text.toString()
                        CoroutineScope(Dispatchers.IO).launch {
                            db.alimentoDao().update(Alimento(id, nomeCorrigido, carboCorrigido))
                        }
                        Toast.makeText(applicationContext, "Correção realizada com sucesso!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(applicationContext, TelaPrincipalActivity::class.java)
                        finish()
                        startActivity(intent)

                    }

                }

            }

        }


    }


}