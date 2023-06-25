package com.example.contagemdecarboidrato

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.contagemdecarboidrato.databinding.ActivityTelaPrincipalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TelaPrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "carbo_por_grama_database"
        ).build()

        val autoComplete1 = binding.autoCompleteTextView1
        val autoComplete2 = binding.autoCompleteTextView2
        val autoComplete3 = binding.autoCompleteTextView3
        val autoComplete4 = binding.autoCompleteTextView4
        val autoComplete5 = binding.autoCompleteTextView5
        val autoComplete6 = binding.autoCompleteTextView6
        val bt_Ircadastro = binding.btIrcadastro

        CoroutineScope(Dispatchers.Main).launch {
            //no DAO necessita de suspend fun, senão o aplicativo quebra
            val alimentos = db.alimentoDao().getAll()

            val adapter = ArrayAdapter(applicationContext, R.layout.select_dialog_item, alimentos)

            autoComplete1.threshold = 2
            autoComplete1.setAdapter(adapter)

            autoComplete2.threshold = 2
            autoComplete2.setAdapter(adapter)

            autoComplete3.threshold = 2
            autoComplete3.setAdapter(adapter)

            autoComplete4.threshold = 2
            autoComplete4.setAdapter(adapter)

            autoComplete5.threshold = 2
            autoComplete5.setAdapter(adapter)

            autoComplete6.threshold = 2
            autoComplete6.setAdapter(adapter)
        }

        bt_Ircadastro.setOnClickListener {
            val intent = Intent(this, CadastrarAlimentoActivity::class.java)
            startActivity(intent)
        }

    }

}