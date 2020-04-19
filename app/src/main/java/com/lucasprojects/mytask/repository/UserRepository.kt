package com.lucasprojects.mytask.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.lucasprojects.mytask.constants.DataBaseConstants
import com.lucasprojects.mytask.entities.UserEntity
import com.lucasprojects.mytask.repository.helper.TaskDataBaseHelper

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

    /**  Carrega usuário */
    fun get(name: String, password: String): UserEntity? {
        var userEntity: UserEntity? = null

        try {
            val cursor: Cursor
            val db = this.mUltraTaskDataBaseHelper.readableDatabase
            val projection = arrayOf(DataBaseConstants.USER.COLUMNS.ID, DataBaseConstants.USER.COLUMNS.NAME)
            val selection = " ${DataBaseConstants.USER.COLUMNS.NAME} = ? AND ${DataBaseConstants.USER.COLUMNS.PASSWORD} = ?"
            val selectionArgs = arrayOf(name, password)

            cursor = db.query(DataBaseConstants.USER.TABLE_NAME, projection, selection, selectionArgs, null, null, null)

            if (cursor.count > 0) {
                cursor.moveToFirst()
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.USER.COLUMNS.ID))
                userEntity = UserEntity(id, name)
            }

            cursor.close()
        } catch (e: Exception) {
            return userEntity
        }
        return userEntity
    }

    /** Verifica se já existe o usuário */
    fun isUserExistent(name: String): Boolean {
        val ret: Boolean

        try {
            val cursor: Cursor
            val db = mUltraTaskDataBaseHelper.readableDatabase
            val projection = arrayOf(DataBaseConstants.USER.COLUMNS.ID)
            val selection = DataBaseConstants.USER.COLUMNS.NAME + " = ?"
            val selectionArgs = arrayOf(name)

            cursor = db.query(DataBaseConstants.USER.TABLE_NAME, projection, selection, selectionArgs, null, null, null)
            ret = cursor.count > 0

            cursor.close()
        } catch (e: Exception) {
            throw e
        }
        return ret
    }

    /** Faz a inserção do usuário */
    fun insert(name: String, password: String): Int {

        try {
            val db = mUltraTaskDataBaseHelper.writableDatabase
            val insertValues = ContentValues()
            insertValues.put(DataBaseConstants.USER.COLUMNS.NAME, name)
            insertValues.put(DataBaseConstants.USER.COLUMNS.PASSWORD, password)

            return db.insert(DataBaseConstants.USER.TABLE_NAME, null, insertValues).toInt()
        } catch (e: Exception) {
            throw e
        }
    }
}