package com.dicoding.medikaltech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.dicoding.medikaltech.data.User

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var edtName:EditText
    private lateinit var edtAge:EditText
    private lateinit var rgGender:RadioGroup




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edt_name)
        edtAge = findViewById(R.id.edt_age)
        rgGender = findViewById(R.id.rg_gender)
        //edtGender = findViewById(R.id.edt_gender)


        button = findViewById(R.id.btn_main)
        button.setOnClickListener{
            val edtName = edtName.text.toString().trim()
            val edtAge = edtAge.text.toString().trim()
            val edtGender = selectGender().toString().trim()
            val user = User(edtName,edtAge, edtGender)
            Log.d("bbb", "onCreate:$user ")
            val moveIntent = Intent(this@MainActivity, FormDiabetes::class.java)
            moveIntent.putExtra(FormDiabetes.DARI_MAIN,user)
            startActivity(moveIntent)
        }

        supportActionBar?.hide()
    }
    fun selectGender():Int{
        var values = 0
        if (rgGender.checkedRadioButtonId>0){
            when(rgGender.checkedRadioButtonId){
                R.id.rd_male -> values = 1
                R.id.rd_female -> values = 0
            }
        }
        return values
    }
}