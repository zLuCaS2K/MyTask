package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.mytask.R
import kotlinx.android.synthetic.main.activity_opening.*

class OpeningActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)

        setListeners()
    }

    private fun setListeners() {
        btnEnter.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnEnter -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.btnRegister -> startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
