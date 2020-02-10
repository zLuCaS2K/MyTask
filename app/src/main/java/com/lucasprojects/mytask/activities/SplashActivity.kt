package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.mytask.R

/**
 * A SplashActivity é a activity responsável por mostrar uma pequena e rápida tela de abertura,
 * semelhante ao que o facebook, instagram e whatssap começaram a fazer.
 * */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /** Esse algoritmo chama a Slide Activity após 1000 milisegundos (1 Segundo)*/
        Handler().postDelayed({
            startActivity(Intent(this, SlideActivity::class.java))
            finish()
        }, 1000)
    }
}
