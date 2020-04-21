package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.mytask.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        appName.startAnimation(animation)

        Handler().postDelayed({
            startActivity(Intent(this, SlideActivity::class.java))
            finish()
        }, 1200)
    }
}
