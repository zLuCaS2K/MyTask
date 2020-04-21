package com.lucasprojects.mytask.business

import android.content.Context
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.UserEntity
import com.lucasprojects.mytask.repository.UserRepository
import com.lucasprojects.mytask.util.SecurityPreferences
import com.lucasprojects.mytask.util.ValidationException

class UserBusiness(val context: Context) {

    private val mUserRepository: UserRepository = UserRepository.getInstance(context)
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)
    private val mMutableListOf = mutableListOf("!", "@", "#", "$", "%", "¨", "&", "(", ")", "|", ",", ".", "<", ">", " ")

    /** Faz o login do usuário e salva no SharedPreferences */
    fun login(name: String, password: String): Boolean {
        val user: UserEntity? = mUserRepository.get(name, password)
        return if (user != null) {
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_ID, user.id.toString())
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_NAME, user.name)
            true
        } else {
            false
        }
    }

    /** Salva o usuário */
    fun save(name: String, password: String) {
        try {
            if (name.isEmpty() || password.isEmpty()) {
                throw ValidationException(context.getString(R.string.all_camps))
            }
            if (mUserRepository.isUserExistent(name)) {
                throw ValidationException(context.getString(R.string.email_in_use))
            }
            for (letter in name) {
                if (mMutableListOf.contains(letter.toString())) {
                    throw ValidationException(context.getString(R.string.name_error))
                }
            }
            val userId = mUserRepository.insert(name, password)
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_ID, userId.toString())
            mSecurityPreferences.setSharedStored(TaskConstants.KEY.USER_NAME, name)
        } catch (e: Exception) {
            throw e
        }
    }
}