package com.lucasprojects.mytask.business

import android.content.Context
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.UserEntity
import com.lucasprojects.mytask.repository.UserRepository
import com.lucasprojects.mytask.util.SecurityPreferences
import com.lucasprojects.mytask.util.ValidationException

class UserBusiness(val context: Context) {

    /** Instância do UserRepository */
    private val mUserRepository: UserRepository = UserRepository.getInstance(context)
    /** Instância do Shared Preferences */
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)
    /** MutableListOf de caracteres especiais para a validação de nome de usuário */
    private val mMutableListOf = mutableListOf(",", ".", "?", "#")

    /**
     * Responsável por verificar se usuário existe e se os dados estão corretos
     * Caso exista, salva dos dados no SharedPreferences para uso posterior
     * */
    fun login(email: String, password: String): Boolean {

        val user: UserEntity? = mUserRepository.get(email, password)
        return if (user != null) {

            /** Salva os dados no SharedPreferences */
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_ID, user.id.toString())
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_NAME, user.name)
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_EMAIL, user.email)

            /** Retorna usuário válido */
            true
        } else {
            false
        }
    }

    /**
     * Salva o usuário no banco de dados e verifica se já existe
     * Caso já exista, lança um erro de email já existente
     * */

    fun save(name: String, email: String, password: String) {
        try {
            /** Verificando se os campos possuem dados */
            if (name == "" || email == "" || password == "") {
                throw ValidationException(context.getString(R.string.all_camps))
            }

            /** Verifica se email já existe no banco de dados */
            if (mUserRepository.isEmailExistent(email)) {
                throw ValidationException(context.getString(R.string.email_in_use))
            }

            /** Verifica se o nome possuí algum caractere especial */
            if (mMutableListOf.contains(name)) {
                throw ValidationException(context.getString(R.string.name_error))
            }

            /** Salva novo usuário, retornando o ID inserido */
            val userId = mUserRepository.insert(name, email, password)

            /** Salva os dados no SharedPreferences */
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_ID, userId.toString())
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_NAME, name)
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_EMAIL, email)
        } catch (e: Exception) {
            throw e
        }
    }
}