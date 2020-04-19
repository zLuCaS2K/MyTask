package com.lucasprojects.mytask.repository

import android.content.Context
import android.database.Cursor
import com.lucasprojects.mytask.constants.DataBaseConstants
import com.lucasprojects.mytask.entities.PriorityEntity
import com.lucasprojects.mytask.repository.helper.TaskDataBaseHelper

class PriorityRepository private constructor(context: Context) {

    private var mUltraTaskDataBaseHelper: TaskDataBaseHelper = TaskDataBaseHelper(context)

    companion object {

        fun getInstance(context: Context): PriorityRepository {
            if (INSTANCE == null) {
                INSTANCE = PriorityRepository(context)
            }
            return INSTANCE as PriorityRepository
        }

        private var INSTANCE: PriorityRepository? = null

    }

    /** Carregamento das prioridades */
    fun getList(): MutableList<PriorityEntity> {
        val list = mutableListOf<PriorityEntity>()
        try {
            val cursor: Cursor
            val db = this.mUltraTaskDataBaseHelper.readableDatabase
            cursor = db.rawQuery("SELECT * FROM ${DataBaseConstants.PRIORITY.TABLE_NAME}", null)

            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.PRIORITY.COLUMNS.ID))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.PRIORITY.COLUMNS.DESCRIPTION))
                    val guestEntity = PriorityEntity(id, description)
                    list.add(guestEntity)
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }
}