package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.business.UserBusiness
import com.lucasprojects.mytask.util.ValidationException
import kotlinx.android.synthetic.main.activity_register.*

/**
 * A RegisterActivity é a activity responsável por efetuar o cadastro de um usuário, para essa
 * acitvity ser chamada todos os usuários precisam ter encerrados suas devidas sessões
 * */

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    /** Váriaveis da Camada Business */
    private lateinit var mUserBusiness: UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        /** Instancia da Classe Business */
        mUserBusiness = UserBusiness(this)
        /** Definindo os listenners do itens */
        setListenner()
    }

    /** Eventos de click */
    private fun setListenner() {
        btnRegister.setOnClickListener(this)
    }

    /** Tratamento dos eventos de click */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnRegister -> handleSave()
        }
    }

    /** Executa o login do usuário */
    private fun handleSave() {
        val userName = editUsername.text.toString()
        val userPassword = editPassword.text.toString()

        try {
            mUserBusiness.save(userName, userPassword)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } catch (e: ValidationException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
