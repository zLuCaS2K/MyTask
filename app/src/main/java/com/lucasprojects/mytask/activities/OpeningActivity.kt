package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_opening.*

/**
 * A OpeningActivity é a activity mais simples, sua única função é possibilitar que o usuário, vá
 * para a LoginActivity ou RegisterActiviry, dependendo se tiver alguma usuário logado no app.
 * */

class OpeningActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)
        /** Definindo os listenners do itens */
        setListeners()
        /** Verifica se tem usuário com sessão ativa */
        verifyLoggedUser()
    }

    /** Eventos de click */
    private fun setListeners() {
        btnEnter.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }

    /** Tratamento dos eventos de click */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnEnter -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.btnRegister -> startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    /** Verificação de usuário logado */
    private fun verifyLoggedUser() {
        val securityPreferences = SecurityPreferences(this)
        val userId = securityPreferences.getSharedStored(TaskConstants.KEY.USER_ID)
        val userEmail = securityPreferences.getSharedStored(TaskConstants.KEY.USER_EMAIL)

        if (userId.isNotEmpty() || userEmail.isNotEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
