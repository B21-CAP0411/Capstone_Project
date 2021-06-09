package com.dicoding.medikaltech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import com.dicoding.medikaltech.data.Diabetes
import com.dicoding.medikaltech.data.Stroke
import com.dicoding.medikaltech.data.User

class FormStroke : AppCompatActivity(){
    private lateinit var button:Button
    private lateinit var rgEverMarried : RadioGroup
    private lateinit var rgWorkType : RadioGroup
    private lateinit var rgResidenceType : RadioGroup
    private lateinit var rgSmokingStatus : RadioGroup
    private lateinit var tvStrokeName : TextView


    companion object{
        const val DARI_MAIN="form stroke"
        const val DARI_DIABETES="from diabetes"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_stroke)
        val dariMain = intent.getParcelableExtra<User>(DARI_MAIN)as User
        val dariDiabet = intent.getParcelableExtra<Diabetes>(DARI_DIABETES) as Diabetes

        rgEverMarried = findViewById(R.id.rg_stroke_everMarried)
        rgWorkType = findViewById(R.id.rg_stroke_workType)
        rgResidenceType = findViewById(R.id.rg_stroke_residenceType)
        rgSmokingStatus = findViewById(R.id.rg_stroke_smokingStatus)
        tvStrokeName = findViewById(R.id.tv_stroke_name)
        tvStrokeName.text = dariMain.name.toString()


        button = findViewById(R.id.btn_stroke)
        button.setOnClickListener{
            val rgEverMarried = everMarriedFunc().toString()
            val rgWorkType = workTypeFunc().toString()
            val rgResidenceType = residenceTypeFunc().toString()
            val rgSmokingStatus = smokingStatusFunc().toString()
            val stroke = Stroke(rgEverMarried,rgWorkType,rgResidenceType,rgSmokingStatus)

            val moveIntent = Intent(this@FormStroke, HeartDisease::class.java)
            moveIntent.putExtra(HeartDisease.DARI_MAIN,dariMain)
            moveIntent.putExtra(HeartDisease.DARI_DIABETES,dariDiabet)
            moveIntent.putExtra(HeartDisease.DARI_STROKE,stroke)

            startActivity(moveIntent)
        }
    }
    private fun everMarriedFunc():Int{
        var values = 0
        if (rgEverMarried.checkedRadioButtonId>0){
            when(rgEverMarried.checkedRadioButtonId){
                R.id.rb_stroke_everMarried_divorce -> values = 1
                R.id.rb_stroke_everMarried_married -> values = 1
                R.id.rb_stroke_everMarried_single -> values = 0
            }
        }
        return values
    }
    private fun workTypeFunc():Int{
        var values = 0
        if (rgWorkType.checkedRadioButtonId>0){
            when(rgWorkType.checkedRadioButtonId){
                R.id.rb_stroke_workType_unemployed -> values = 0
                R.id.rb_stroke_workType_employed -> values = 1
                R.id.rb_stroke_workType_fired -> values = 1
            }
        }
        return values
    }
    private fun residenceTypeFunc():Int{
        var values = 0
        if (rgResidenceType.checkedRadioButtonId>0){
            when(rgResidenceType.checkedRadioButtonId){
                R.id.rb_stroke_residenceType_city -> values = 0
                R.id.rb_stroke_residenceType_village -> values = 1
            }
        }
        return values
    }
    private fun smokingStatusFunc():Int{
        var values = 0
        if (rgSmokingStatus.checkedRadioButtonId>0){
            when(rgSmokingStatus.checkedRadioButtonId){
                R.id.rb_stroke_smokingStatus_never -> values = 0
                R.id.rb_stroke_smokingStatus_smoking -> values = 1
                R.id.rb_stroke_smokingStatus_notAgain -> values = 2
            }
        }
        return values
    }
}
