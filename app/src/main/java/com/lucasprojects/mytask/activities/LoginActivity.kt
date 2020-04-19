package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.business.UserBusiness
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserBusiness: UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mUserBusiness = UserBusiness(this)
        setListenner()
    }

    /** Eventos de click */
    private fun setListenner() {
        btnEnter.setOnClickListener(this)
    }

    /** Tratamento dos eventos de click */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnEnter -> handleLogin()
        }
    }

    /** Executa o login do usuário */
    private fun handleLogin() {
        val name = this.editUsername.text.toString()
        val password = this.editPassword.text.toString()

        if (mUserBusiness.login(name, password)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.data_error, Toast.LENGTH_SHORT).show()
        }
    }
}
