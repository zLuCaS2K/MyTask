package com.lucasprojects.mytask.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.lucasprojects.mytask.constants.DataBaseConstants
import com.lucasprojects.mytask.entities.UserEntity
import com.lucasprojects.mytask.repository.helper.TaskDataBaseHelper

/**
 * Classe com construtor privado, impede que seja instanciada
 * Classe de acesso a dados para Usuário
 * */

class UserRepository private constructor(val context: Context) {

    private var mUltraTaskDataBaseHelper: TaskDataBaseHelper = TaskDataBaseHelper(context)

    companion object {

        fun getInstance(context: Context): UserRepository {
            if (INSTANCE == null) {
                INSTANCE = UserRepository(context)
            }
            return INSTANCE as UserRepository
        }

        private var INSTANCE: UserRepository? = null

    }

    /**
     * Carrega usuário
     */

    fun get(name: String, password: String): UserEntity? {

        var userEntity: UserEntity? = null

        try {
            val cursor: Cursor
            val db = this.mUltraTaskDataBaseHelper.readableDatabase

            // Colunas que serão retornadas
            val projection = arrayOf(DataBaseConstants.USER.COLUMNS.ID, DataBaseConstants.USER.COLUMNS.NAME)

            // Filtro
            val selection = " ${DataBaseConstants.USER.COLUMNS.NAME} = ? AND ${DataBaseConstants.USER.COLUMNS.PASSWORD} = ?"
            val selectionArgs = arrayOf(name, password)

            // Carrega usuário
            cursor = db.query(DataBaseConstants.USER.TABLE_NAME, projection, selection, selectionArgs, null, null, null
            )

            // Verifica se existe retorno
            if (cursor.count > 0) {
                cursor.moveToFirst()

                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.USER.COLUMNS.ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.USER.COLUMNS.NAME))

                // Atribui valor a variável do usuário
                userEntity = UserEntity(id, name)
            }

            // Fecha cursor
            cursor.close()

        } catch (e: Exception) {
            return userEntity
        }

        // Retorno objeto com dados
        return userEntity
    }

    /**
     * Verifica se já existe o usuário
     */
    fun isUserExistent(name: String): Boolean {

        val ret: Boolean
        try {
            val cursor: Cursor
            val db = mUltraTaskDataBaseHelper.readableDatabase

            // Colunas que serão retornadas
            val projection = arrayOf(DataBaseConstants.USER.COLUMNS.ID)

            // Filtro
            val selection = DataBaseConstants.USER.COLUMNS.NAME + " = ?"
            val selectionArgs = arrayOf(name)

            // Carrega usuário - Linha única
            // cursor = db.rawQuery("select * from user where email = '$email'", null)

            cursor = db.query(
                DataBaseConstants.USER.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )
            ret = cursor.count > 0

            // Fecha cursor
            cursor.close()

        } catch (e: Exception) {
            throw e
        }

        // Retorno objeto com dados
        return ret
    }

    /**
     * Faz a inserção do usuário
     * */

    fun insert(name: String, password: String): Int {

        try {
            /** Fazendo escrita dos dados */
            val db = mUltraTaskDataBaseHelper.writableDatabase

            val insertValues = ContentValues()
            insertValues.put(DataBaseConstants.USER.COLUMNS.NAME, name)
            insertValues.put(DataBaseConstants.USER.COLUMNS.PASSWORD, password)

            /** Faz a inserção e retorna */
            val userId = db.insert(DataBaseConstants.USER.TABLE_NAME, null, insertValues).toInt()
            return userId

        } catch (e: Exception) {
            throw e
        }
    }
}