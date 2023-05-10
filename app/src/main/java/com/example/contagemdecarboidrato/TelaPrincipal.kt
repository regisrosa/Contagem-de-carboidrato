package com.example.contagemdecarboidrato

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.contagemdecarboidrato.databinding.ActivityTelaPrincipalBinding

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val autoComplete1 = binding.autoCompleteTextView1
        val autoComplete2 = binding.autoCompleteTextView2
        val autoComplete3 = binding.autoCompleteTextView3
        val autoComplete4 = binding.autoCompleteTextView4
        val autoComplete5 = binding.autoCompleteTextView5
        val autoComplete6 = binding.autoCompleteTextView6

        val nome = arrayOf("Regis", "Miguel", "Bruno")

        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, nome)

        autoComplete1.threshold=2
        autoComplete1.setAdapter(adapter)

        autoComplete2.threshold=2
        autoComplete2.setAdapter(adapter)

        autoComplete3.threshold=2
        autoComplete3.setAdapter(adapter)

        autoComplete4.threshold=2
        autoComplete4.setAdapter(adapter)

        autoComplete5.threshold=2
        autoComplete5.setAdapter(adapter)

        autoComplete6.threshold=2
        autoComplete6.setAdapter(adapter)

    }
}