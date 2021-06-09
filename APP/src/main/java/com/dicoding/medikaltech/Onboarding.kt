package com.dicoding.medikaltech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Onboarding : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportActionBar?.hide()

        val btnMoveActivity: Button = findViewById(R.id.button_started)
        btnMoveActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_started -> {
                val moveIntent = Intent(this@Onboarding, MainActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}