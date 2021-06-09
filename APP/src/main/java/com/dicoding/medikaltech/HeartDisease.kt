package com.dicoding.medikaltech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import com.dicoding.medikaltech.data.Diabetes
import com.dicoding.medikaltech.data.Heart
import com.dicoding.medikaltech.data.Stroke
import com.dicoding.medikaltech.data.User

class HeartDisease : AppCompatActivity(){
    private lateinit var button: Button
    private lateinit var rgHeartDisease: RadioGroup
    private lateinit var rgHeartdCp: RadioGroup
    private lateinit var edtTrestBps: EditText
    private lateinit var edtBloodPressure: EditText
    private lateinit var edtChol : EditText
    private lateinit var edtFbs: EditText
    private lateinit var rgRestecg : RadioGroup
    private lateinit var edtThalach : EditText
    private lateinit var rgExang : RadioGroup
    private lateinit var edtOldPeak : EditText
    private lateinit var rgSlope : RadioGroup
    private lateinit var edtCa : EditText
    private lateinit var rgThal : RadioGroup
    private lateinit var tvHeartDiseaseName:TextView

    companion object{
        const val DARI_MAIN = "dari main"
        const val DARI_DIABETES = "dari diabet"
        const val DARI_STROKE = "dari stroke"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_disease)

        val dariMain = intent.getParcelableExtra<User>(DARI_MAIN) as User
        val dariDiabetes = intent.getParcelableExtra<Diabetes>(DARI_DIABETES) as Diabetes
        val dariStroke = intent.getParcelableExtra<Stroke>(DARI_STROKE) as Stroke

        rgHeartDisease = findViewById(R.id.rg_heartD_heartDisease)
        rgHeartdCp = findViewById(R.id.rg_heartD_cp)
        edtTrestBps = findViewById(R.id.edt_heartD_trestbps)
        edtBloodPressure = findViewById(R.id.edt_heartD_bloodPressure)
        edtChol = findViewById(R.id.edt_heartD_chol)
        edtFbs = findViewById(R.id.edt_heartD_fbs)
        rgRestecg = findViewById(R.id.rg_heartD_restecg)
        edtThalach = findViewById(R.id.edt_heartD_thalach)
        rgExang = findViewById(R.id.rg_heartD_exang)
        edtOldPeak = findViewById(R.id.edt_heartD_oldPeak)
        rgSlope = findViewById(R.id.rg_heartD_slope)
        edtCa = findViewById(R.id.edt_heartD_ca)
        rgThal = findViewById(R.id.rg_heartD_thal)
        button = findViewById(R.id.btn_heart)
        tvHeartDiseaseName = findViewById(R.id.tv_heart_name)
        tvHeartDiseaseName.text = dariMain.name.toString()

        button.setOnClickListener{
            val rgHeartDisease = heartDiseaseFunc().toString()
            val rgHeartdCp = rgCpFunc().toString()
            val edtTrestBps = edtTrestBps.text.toString().trim()
            val edtBloodPressure = edtBloodPressure.text.toString().trim()
            val edtChol = edtChol.text.toString().trim()
            val edtFbs = cekFbs(edtFbs.text.toString()).toString()
            val rgRestecg = rgRestecgFunc().toString()
            val edtThalach = edtThalach.text.toString().trim()
            val rgExang = rgExangFunc().toString()
            val edtOldPeak = edtOldPeak.text.toString().trim()
            val rgSlope = rgSlopeFunc().toString()
            val edtCa = edtCa.text.toString().trim()
            val rgThal = rgThalFunc().toString()
            val hypertensi = hypertensiFunc(edtTrestBps,edtBloodPressure).toString()
            val heart = Heart(rgHeartDisease,
                hypertensi,
                rgHeartdCp,
                edtTrestBps,
                edtBloodPressure,
                edtChol,
                edtFbs,
                rgRestecg,
                edtThalach,
                rgExang,
                edtOldPeak,
                rgSlope,
                edtCa,
                rgThal
            )

            val moveIntent = Intent(this@HeartDisease, Lifestyle::class.java)
            moveIntent.putExtra(Lifestyle.DARI_MAIN,dariMain)
            moveIntent.putExtra(Lifestyle.DARI_DIABETES,dariDiabetes)
            moveIntent.putExtra(Lifestyle.DARI_STROKE,dariStroke)
            moveIntent.putExtra(Lifestyle.DARI_HEARTDESEASE,heart)
            startActivity(moveIntent)
        }
    }

    private fun heartDiseaseFunc():Int{
        var values = 0
        if (rgHeartDisease.checkedRadioButtonId>0){
            when(rgHeartDisease.checkedRadioButtonId){
                R.id.rb_heartD_heartDisease_yes -> values = 1
                R.id.rb_heartD_heartDisease_no -> values = 0
            }
        }
        return values
    }
    private fun rgCpFunc():Int{
        var values = 0
        if (rgHeartdCp.checkedRadioButtonId>0){
            when(rgHeartdCp.checkedRadioButtonId){
                R.id.rb_heartD_cp_1 -> values = 1
                R.id.rb_heartD_cp_2 -> values = 2
                R.id.rb_heartD_cp_3 -> values = 3
                }
            }
        return values
    }
    private fun rgRestecgFunc():Int{
        var values = 0
        if (rgRestecg.checkedRadioButtonId>0){
            when(rgRestecg.checkedRadioButtonId){
                R.id.rb_heartD_restecg_normal -> values = 0
                R.id.rb_heartD_restecg_unknown -> values = 0
                R.id.rb_heartD_restecg_abnormal -> values = 1
                R.id.rb_heartD_restecg_penebalanJantung -> values=2
            }
        }
        return values
    }
    private fun rgExangFunc():Int{
        var values = 0
        if (rgExang.checkedRadioButtonId>0){
            when(rgExang.checkedRadioButtonId){
                R.id.rb_heartD_exang_no -> values = 0
                R.id.rb_heartD_exang_yes -> values = 1
            }
        }
        return values
    }
    private fun rgSlopeFunc():Int{
        var values = 0
        if (rgSlope.checkedRadioButtonId>0){
            when(rgSlope.checkedRadioButtonId){
                R.id.rb_heartD_slope_opendown -> values = 0
                R.id.rb_heartD_slope_flat -> values = 1
                R.id.rb_heartD_slope_openup -> values = 2
            }
        }
        return values
    }
    private fun rgThalFunc():Int{
        var values = 0
        if (rgThal.checkedRadioButtonId>0){
            when(rgThal.checkedRadioButtonId){
                R.id.rb_heartD_thal_normal -> values = 0
                R.id.rb_heartD_thal_genetic -> values = 1
                R.id.rb_heartD_thal_temporary -> values = 2
            }
        }
        return values
    }
    private fun hypertensiFunc(trest:String,bp:String):Int{
        val a = trest.toFloat()
        val b = bp.toFloat()
        val result = a/b
        var hasil = 0
        if (result>140/90){
            hasil = 1
        }else{
            hasil = 0
        }
        return hasil
    }
    private fun cekFbs(fbs:String):Int{
        var values = fbs.toInt()
        var result = 0
        if (values>120){
            result = 1
        }else{
            result = 0
        }
        return result
    }
}