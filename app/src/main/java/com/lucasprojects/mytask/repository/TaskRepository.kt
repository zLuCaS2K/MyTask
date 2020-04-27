package com.lucasprojects.mytask.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.constants.DataBaseConstants
import com.lucasprojects.mytask.repository.helper.TaskDataBaseHelper

class TaskRepository private constructor(context: Context) {

    private var mUltraTaskDataBaseHelper: TaskDataBaseHelper = TaskDataBaseHelper(context)

    companion object {
        fun getInstance(context: Context): TaskRepository {
            if (INSTANCE == null) {
                INSTANCE = TaskRepository(context)
            }
            return INSTANCE as TaskRepository
        }

        private var INSTANCE: TaskRepository? = null

    }

    /** Carregamento de uma task */
    fun get(taskID: Int): TaskEntity? {
        var taskEntity: TaskEntity? = null

        try {
            val cursor: Cursor
            val db = this.mUltraTaskDataBaseHelper.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.TASK.COLUMNS.USERID
                , DataBaseConstants.TASK.COLUMNS.NAME
                , DataBaseConstants.TASK.COLUMNS.DUEDATE
                , DataBaseConstants.TASK.COLUMNS.TEXT
                , DataBaseConstants.TASK.COLUMNS.PRIORITYID
                , DataBaseConstants.TASK.COLUMNS.COMPLETE
            )
            val selection = "${DataBaseConstants.TASK.COLUMNS.ID} = ?"
            val selectionArgs = arrayOf(taskID.toString())

            cursor = db.query(DataBaseConstants.TASK.TABLE_NAME, projection, selection, selectionArgs, null, null, null)

            if (cursor.count > 0) {
                cursor.moveToFirst()

                val userId = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.USERID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.NAME))
                val text = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.TEXT))
                val dueDate = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.DUEDATE))
                val priorityId = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.PRIORITYID))
                val complete = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.COMPLETE)) == 1)

                taskEntity = TaskEntity(taskID, userId, priorityId, name, text, dueDate, complete)
            }
            cursor.close()

        } catch (e: Exception) {
            return taskEntity
        }
        return taskEntity
    }

    /** Carregamento das task de acordo com o ID do usuário */
    fun getList(filter: Int, userId: Int): MutableList<TaskEntity> {
        val list = mutableListOf<TaskEntity>()

        try {
            val cursor: Cursor
            val db = this.mUltraTaskDataBaseHelper.readableDatabase
            cursor = db.rawQuery(
                "SELECT * FROM ${DataBaseConstants.TASK.TABLE_NAME} WHERE " +
                        "${DataBaseConstants.TASK.COLUMNS.COMPLETE} = $filter" +
                        " AND ${DataBaseConstants.TASK.COLUMNS.USERID} = $userId",
                null
            )
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val taskID = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.NAME))
                    val text = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.TEXT))
                    val dueDate = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.DUEDATE))
                    val priorityId = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.PRIORITYID))
                    val complete = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.COMPLETE)) == 1)
                    list.add(TaskEntity(taskID, userId, priorityId, name, text, dueDate, complete))
                }
            }
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    /** Faz a inserção da tarefa */
    fun insert(task: TaskEntity) {
        try {
            val db = mUltraTaskDataBaseHelper.writableDatabase
            val complete: Int = if (task.complete) 1 else 0
            val insertValues = ContentValues()
            insertValues.put(DataBaseConstants.TASK.COLUMNS.USERID, task.userId)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.PRIORITYID, task.priorityId)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.NAME, task.name)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.TEXT, task.text)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.DUEDATE, task.dueDate)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.COMPLETE, complete)

            db.insert(DataBaseConstants.TASK.TABLE_NAME, null, insertValues)

        } catch (e: Exception) {
            throw e
        }
    }

    /** Faz a atualização da tarefa */
    fun update(task: TaskEntity) {
        try {
            val db = mUltraTaskDataBaseHelper.writableDatabase
            val complete: Int = if (task.complete) 1 else 0
            val updateValues = ContentValues()
            updateValues.put(DataBaseConstants.TASK.COLUMNS.USERID, task.userId)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.NAME, task.name)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.TEXT, task.text)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.DUEDATE, task.dueDate)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.PRIORITYID, task.priorityId)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.COMPLETE, complete)

            val selection = DataBaseConstants.TASK.COLUMNS.ID + " = ?"
            val selectionArgs = arrayOf(task.id.toString())

            db.update(DataBaseConstants.TASK.TABLE_NAME, updateValues, selection, selectionArgs)
        } catch (e: Exception) {
            throw e
        }
    }

    /** Faz a remoção da tarefa */
    fun delete(id: Int) {
        try {
            val db = mUltraTaskDataBaseHelper.writableDatabase
            val whereClause = "${DataBaseConstants.TASK.COLUMNS.ID} = ?"
            val whereArgs = arrayOf(id.toString())

            db.delete(DataBaseConstants.TASK.TABLE_NAME, whereClause, whereArgs)
        } catch (e: Exception) {
            throw e
        }
    }

    /** Atualiza tarefa como completa ou pendente */
    fun complete(id: Int, complete: Boolean) {
        val task: TaskEntity? = get(id)
        if (task != null) {
            task.complete = complete
            update(task)
        }
    }
}