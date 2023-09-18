package com.example.contagemdecarboidrato

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.contagemdecarboidrato.databinding.ActivityTelaPrincipalBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class TelaPrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //------------IDENTIFICANDO OS ELEMENTOS DE TELA---------------------
        val bt_Ircadastro = binding.btIrcadastro
        val bt_calcular = binding.btCalcular

        val autoComplete1 = binding.autoCompleteTextView1
        val autoComplete2 = binding.autoCompleteTextView2
        val autoComplete3 = binding.autoCompleteTextView3
        val autoComplete4 = binding.autoCompleteTextView4
        val autoComplete5 = binding.autoCompleteTextView5
        val autoComplete6 = binding.autoCompleteTextView6
        autoComplete1.isEnabled = false
        autoComplete2.isEnabled = false
        autoComplete3.isEnabled = false
        autoComplete4.isEnabled = false
        autoComplete5.isEnabled = false
        autoComplete6.isEnabled = false
        autoComplete1.setHint("Clique no checkbox")
        autoComplete2.setHint("Clique no checkbox")
        autoComplete3.setHint("Clique no checkbox")
        autoComplete4.setHint("Clique no checkbox")
        autoComplete5.setHint("Clique no checkbox")
        autoComplete6.setHint("Clique no checkbox")

        val et_1 = binding.et1
        val et_2 = binding.et2
        val et_3 = binding.et3
        val et_4 = binding.et4
        val et_5 = binding.et5
        val et_6 = binding.et6
        et_1.isEnabled = false
        et_2.isEnabled = false
        et_3.isEnabled = false
        et_4.isEnabled = false
        et_5.isEnabled = false
        et_6.isEnabled = false

        val cb1 = binding.checkBox1
        val cb2 = binding.checkBox2
        val cb3 = binding.checkBox3
        val cb4 = binding.checkBox4
        val cb5 = binding.checkBox5
        val cb6 = binding.checkBox6
        //----------------------------------------------------------------------

        //-----Lógica para habilitar/desabilitar os campos de preenchimento-----
        cb1.setOnClickListener {
            if (cb1.isChecked) {
                autoComplete1.isEnabled = true
                autoComplete1.setHint("Nome do alimento")
                et_1.isEnabled = true
            } else {
                autoComplete1.isEnabled = false
                autoComplete1.setHint("Clique no checkbox")
                et_1.isEnabled = false
            }
        }

        cb2.setOnClickListener {
            if (cb2.isChecked) {
                autoComplete2.isEnabled = true
                autoComplete2.setHint("Nome do alimento")
                et_2.isEnabled = true
            } else {
                autoComplete2.isEnabled = false
                autoComplete2.setHint("Clique no checkbox")
                et_2.isEnabled = false
            }
        }

        cb3.setOnClickListener {
            if (cb3.isChecked) {
                autoComplete3.isEnabled = true
                autoComplete3.setHint("Nome do alimento")
                et_3.isEnabled = true
            } else {
                autoComplete3.isEnabled = false
                autoComplete3.setHint("Clique no checkbox")
                et_3.isEnabled = false
            }
        }

        cb4.setOnClickListener {
            if (cb4.isChecked) {
                autoComplete4.isEnabled = true
                autoComplete4.setHint("Nome do alimento")
                et_4.isEnabled = true
            } else {
                autoComplete4.isEnabled = false
                autoComplete4.setHint("Clique no checkbox")
                et_4.isEnabled = false
            }
        }

        cb5.setOnClickListener {
            if (cb5.isChecked) {
                autoComplete5.isEnabled = true
                autoComplete5.setHint("Nome do alimento")
                et_5.isEnabled = true
            } else {
                autoComplete5.isEnabled = false
                autoComplete5.setHint("Clique no checkbox")
                et_5.isEnabled = false
            }
        }

        cb6.setOnClickListener {
            if (cb6.isChecked) {
                autoComplete6.isEnabled = true
                autoComplete6.setHint("Nome do alimento")
                et_6.isEnabled = true
            } else {
                autoComplete6.isEnabled = false
                autoComplete6.setHint("Clique no checkbox")
                et_6.isEnabled = false
            }

        }
        //-----------------------------------------------------------------

        //-----Lógica do cardview customizado------------------
        var c_layout = binding.cLayout
        c_layout.isVisible = false
        var tv_res = binding.tvRes
        var sair = binding.btSair
        //------------------------------------------------------

        db = AppDatabase.getDatabase(this)!!

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


        bt_calcular.setOnClickListener {
            var res: Double = 0.0
            var lista_resultados: ArrayList<Double> = arrayListOf()

            CoroutineScope(Dispatchers.Main).launch {

                if (cb1.isChecked) {
                    if (autoComplete1.text.toString() != "" && et_1.text.toString() != "") {
                        val alimento_1 = autoComplete1.text.toString()
                        val valor_1 = db.alimentoDao().carboPorGrama(alimento_1)
                        var carbo_1 = valor_1.toString().toDouble()
                        var peso_1 = et_1.text.toString().toInt()
                        val res1 = carbo_1 * peso_1

                        lista_resultados.add(res1)
                    } else {
                        snackbar(it)
                    }
                }

                if (cb2.isChecked) {
                    if (autoComplete2.text.toString() != "" && et_2.text.toString() != "") {
                        val alimento_2 = autoComplete2.text.toString()
                        val valor_2 = db.alimentoDao().carboPorGrama(alimento_2)
                        var carbo_2 = valor_2.toString().toDouble()
                        var peso_2 = et_2.text.toString().toInt()
                        val res2 = carbo_2 * peso_2

                        lista_resultados.add(res2)
                    } else {
                        snackbar(it)
                    }

                }

                if (cb3.isChecked) {
                    if (autoComplete3.text.toString() != "" && et_3.text.toString() != "") {
                        val alimento_3 = autoComplete3.text.toString()
                        val valor_3 = db.alimentoDao().carboPorGrama(alimento_3)
                        var carbo_3 = valor_3.toString().toDouble()
                        var peso_3 = et_3.text.toString().toInt()
                        val res3 = carbo_3 * peso_3

                        lista_resultados.add(res3)
                    } else {
                        snackbar(it)
                    }

                }

                if (cb4.isChecked) {
                    if (autoComplete4.text.toString() != "" && et_4.text.toString() != "") {
                        val alimento_4 = autoComplete4.text.toString()
                        val valor_4 = db.alimentoDao().carboPorGrama(alimento_4)
                        var carbo_4 = valor_4.toString().toDouble()
                        var peso_4 = et_4.text.toString().toInt()
                        val res4 = carbo_4 * peso_4

                        lista_resultados.add(res4)
                    } else {
                        snackbar(it)
                    }

                }

                if (cb5.isChecked) {
                    if (autoComplete5.text.toString() != "" && et_5.text.toString() != "") {
                        val alimento_5 = autoComplete5.text.toString()
                        val valor_5 = db.alimentoDao().carboPorGrama(alimento_5)
                        var carbo_5 = valor_5.toString().toDouble()
                        var peso_5 = et_5.text.toString().toInt()
                        val res5 = carbo_5 * peso_5

                        lista_resultados.add(res5)
                    } else {
                        snackbar(it)
                    }

                }

                if (cb6.isChecked) {
                    if (autoComplete6.text.toString() != "" && et_6.text.toString() != "") {
                        val alimento_6 = autoComplete6.text.toString()
                        val valor_6 = db.alimentoDao().carboPorGrama(alimento_6)
                        var carbo_6 = valor_6.toString().toDouble()
                        var peso_6 = et_6.text.toString().toInt()
                        val res6 = carbo_6 * peso_6

                        lista_resultados.add(res6)
                    } else {
                        snackbar(it)
                    }

                }

                lista_resultados.forEach {
                    res = it.plus(res)
                }

                c_layout.isVisible = true
                tv_res.setText(res.roundToInt().toString() + " g")
                sair.setOnClickListener {
                    c_layout.isVisible = false
                }

            }


        }


    }

    fun snackbar(view: View) {

        Snackbar.make(view, "Preencha todos os campos da linha.", Snackbar.LENGTH_SHORT)
            .setBackgroundTint(Color.RED).show()
    }

    fun irCorrigir(view: View){
        val intent = Intent(this, CorrigirActivity::class.java)
        startActivity(intent)
    }


}