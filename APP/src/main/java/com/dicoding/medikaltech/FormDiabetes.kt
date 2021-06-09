package com.dicoding.medikaltech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.dicoding.medikaltech.data.Diabetes
import com.dicoding.medikaltech.data.User


class FormDiabetes : AppCompatActivity(){
    private lateinit var btnMoveActivity: Button
    private lateinit var edtPregnancies: EditText
    private lateinit var edtSkinThickness: EditText
    private lateinit var edtInsulin: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtWeight: EditText
    private lateinit var rgPredigreeFunction:RadioGroup
    private lateinit var tvName: TextView

    companion object{
        const val DARI_MAIN = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_diabetes)
        val dariMain = intent.getParcelableExtra<User>(DARI_MAIN) as User

        edtPregnancies = findViewById(R.id.edt_diabet_pregnancies)
        edtSkinThickness = findViewById(R.id.edt_diabet_skinThickness)
        edtInsulin = findViewById(R.id.edt_diabet_insulin)
        edtHeight = findViewById(R.id.edt_height)
        edtWeight = findViewById(R.id.edt_weight)
        rgPredigreeFunction = findViewById(R.id.rg_diabet_predigreeFunction)
        btnMoveActivity = findViewById(R.id.btn_diabet)
        tvName = findViewById(R.id.tv_nameDiabetes)
        tvName.text = dariMain.name.toString()

        if (dariMain.gender=="1"){
            //edtPregnancies.isVisible = false
            edtPregnancies.setText("0")
            edtPregnancies.isEnabled = false
        }

        btnMoveActivity.setOnClickListener{
            val edtPregnancies = edtPregnancies.text.toString().trim()
            val edtSkinThickness = edtSkinThickness.text.toString().trim()
            val edtInsulin = edtInsulin.text.toString().trim()
            val edtHeight = edtHeight.text.toString().trim()
            val edtWeight = edtWeight.text.toString().trim()
            val rgPredigreeFunction = predigreeFunc().toString()
            val bmi = hitungBmi(edtWeight.toDouble(),edtHeight.toDouble()).toString()
            val diabetes = Diabetes(edtPregnancies,
                edtSkinThickness,
                edtInsulin,
                edtHeight,
                edtWeight,
                rgPredigreeFunction,
                bmi
            )
            val moveIntent = Intent(this@FormDiabetes, FormStroke::class.java)
            moveIntent.putExtra(FormStroke.DARI_MAIN,dariMain)
            moveIntent.putExtra(FormStroke.DARI_DIABETES,diabetes)
            startActivity(moveIntent)
        }
    }
    private fun hitungBmi(berat:Double, tinggi:Double):Double{
        var hasil = 0.0
        hasil = berat/((tinggi*tinggi)/100)
        return hasil.toDouble()
    }
    private fun predigreeFunc():Int{
            var values = 0
            if (rgPredigreeFunction.checkedRadioButtonId>0){
                when(rgPredigreeFunction.checkedRadioButtonId){
                    R.id.rb_diabet_predigreeFunction_yes -> values = 1
                    R.id.rb_diabet_predigreeFunction_no-> values = 0
                }
            }
            return values
        }
    }
