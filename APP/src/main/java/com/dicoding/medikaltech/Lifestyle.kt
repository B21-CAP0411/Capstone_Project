package com.dicoding.medikaltech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.dicoding.medikaltech.data.Diabetes
import com.dicoding.medikaltech.data.Heart
import com.dicoding.medikaltech.data.Stroke
import com.dicoding.medikaltech.data.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import java.lang.Exception
import kotlin.math.log


class Lifestyle : AppCompatActivity() {
    //private lateinit var listUser : ArrayList<User>
    private var listDiabetes: List<Number?> = listOf()
    private var listHeartDisease: List<Number?> = listOf()
    private var listStroke : List<Number?> = listOf()
    private lateinit var tvDiabetStatus:TextView
    private lateinit var tvStrokeStatus:TextView
    private lateinit var tvHeartDiseaseStatus:TextView
    private lateinit var tvRecommendation : TextView
    private lateinit var pbLifestyle : ProgressBar
    private lateinit var tvLifestyle : TextView

    companion object{
        const val DARI_MAIN = "extra_user"
        const val DARI_DIABETES = "dariDiabetes"
        const val DARI_STROKE = "dariStroke"
        const val DARI_HEARTDESEASE = "dariHeartDesease"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifestyle)

        tvDiabetStatus = findViewById(R.id.tv_lifestyle_diabetesStat)
        tvStrokeStatus = findViewById(R.id.tv_lifestyle_strokeStat)
        tvHeartDiseaseStatus = findViewById(R.id.tv_lifestyle_heaertDiseaseStat)
        tvRecommendation = findViewById(R.id.tv_lifestyle_recommendation)
        pbLifestyle = findViewById(R.id.pb_lifeStyle)
        tvLifestyle = findViewById(R.id.tv_lifestyle_name)


        val dariMain = intent.getParcelableExtra<User>(DARI_MAIN) as User
        val dariDiabetes = intent.getParcelableExtra<Diabetes>(DARI_DIABETES) as Diabetes
        val dariStroke = intent.getParcelableExtra<Stroke>(DARI_STROKE) as Stroke
        val dariHeartDisease = intent.getParcelableExtra<Heart>(DARI_HEARTDESEASE) as Heart

        tvLifestyle.text =dariMain.name.toString()

        //heartDisease
        val age = dariMain.age?.toInt()
        val sex = dariMain.gender?.toInt()
        val cp = dariHeartDisease.rgHeartdCp?.toInt()
        val trestbps = dariHeartDisease.edtTrestBps?.toInt()
        val chol = dariHeartDisease.edtChol?.toInt()
        val fbs = dariHeartDisease.edtFbs?.toInt()
        val restecg = dariHeartDisease.rgRestecg?.toInt()
        val thalach = dariHeartDisease.edtThalach?.toInt()
        val exang = dariHeartDisease.rgExang?.toInt()
        val oldpeak = dariHeartDisease.edtOldPeak?.toInt()
        val slope = dariHeartDisease.rgSlope?.toInt()
        val ca = dariHeartDisease.edtCa?.toInt()
        val thal = dariHeartDisease.rgThal?.toInt()

        //stroke
        //sex
        //age
        val hypertension = dariHeartDisease.hypertensi?.toFloat()
        val heartDisease = dariHeartDisease.rgHeartDisease?.toInt()
        val everMarried = dariStroke.rgEverMarried?.toInt()
        val workType = dariStroke.rgWorkType?.toInt()
        val residenceType = dariStroke.rgResidenceType?.toInt()
        val avgGlucose = dariHeartDisease.edtFbs?.toInt()
        val bmi = dariDiabetes.bmi?.toFloat()
        val smokingStat = dariStroke.rgSmokingStatus?.toInt()

        //diabetes parameter
        val pregnancies = dariDiabetes.edtPregnancies?.toInt()
        val glucose = dariHeartDisease.edtFbs?.toInt()
        val bloodPressure = dariHeartDisease.edtBloodPressure?.toInt()
        val skinThickness = dariDiabetes.edtSkinThickness?.toInt()
        val insulin = dariDiabetes.edtInsulin?.toInt()
        //bmi
        val diabetesPredigree = dariDiabetes.rgPredigreeFunction?.toInt()
        //age

        listHeartDisease = listOf(age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal)
        listStroke = listOf(sex,age,hypertension,heartDisease,everMarried,workType,residenceType,avgGlucose,bmi,smokingStat)
        listDiabetes = listOf(pregnancies,glucose,bloodPressure,skinThickness,insulin,bmi,diabetesPredigree,age)
        pbLifestyle.visibility = View.VISIBLE
        apiPost(listDiabetes,listStroke,listHeartDisease)

        supportActionBar?.hide()
    }

    private fun apiPost(diabet: List<Number?>,stroke: List<Number?>, heart: List<Number?>){
        val client = AsyncHttpClient()
        val param = JSONObject()

        val entity:StringEntity
            param.put("diabetes", diabet)
            param.put("heart", heart)
            param.put("stroke", stroke)

        Log.d("sss", "apiPost:$param ")

        val url = "https://us-central1-ace-forest-316012.cloudfunctions.net/predict_plat"
        entity = StringEntity(param.toString())
        client.post(baseContext,url,entity,"application/json",object :AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                Log.d("berhasil", "onSuccess: ${String(responseBody)}")
                val result = String(responseBody)
                    val jsonObject = JSONObject(result)
                    val a = jsonObject.getInt("prediction_diabetes")
                    val b = jsonObject.getInt("prediction_heart")
                    val c = jsonObject.getInt("prediction_stroke")
                    Log.d("json", "onSuccess: $a, $b, $c")
                status(a,tvDiabetStatus)
                status(b,tvHeartDiseaseStatus)
                status(c,tvStrokeStatus)
                rekomendasi(a,b,c,tvRecommendation)
                pbLifestyle.visibility = View.INVISIBLE
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.d("gagal", "onFailure: ${error}")
                Toast.makeText(this@Lifestyle,"connection failed",Toast.LENGTH_SHORT).show()
                pbLifestyle.visibility = View.INVISIBLE
            }

        })
    }

    private fun status(id:Int,tvStatus:TextView){
        when(id){
            1 -> tvStatus.text = "Yes"
            0 -> tvStatus.text = "No"
        }
    }
    private fun rekomendasi(aDiabet:Int,bHeart:Int,cStroke:Int,tv:TextView){
        if (aDiabet==1 && bHeart==0 && cStroke==0){
            tv.text = getText(R.string.diabetes_recommendation)
        }else if (aDiabet==0 && bHeart==1 && cStroke==0){
            tv.text = getText(R.string.heartdisease_Recommendation)
        }else if (aDiabet==0 && bHeart==0 && cStroke==1){
            tv.text = getText(R.string.stroke_recommendation)
        }else{
            tv.text = "Recommendation not yet available"
        }


    }

}