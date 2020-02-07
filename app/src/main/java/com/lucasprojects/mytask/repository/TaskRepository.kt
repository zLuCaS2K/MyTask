package com.lucasprojects.mytask.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.constants.DataBaseConstants
import com.lucasprojects.mytask.repository.helper.TaskDataBaseHelper

/**
 * Classe com construtor privado, impede que seja instanciada
 * Acesso a dados para Tarefas
 * */
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

    /**
     * Carrega tarefa de acordo com o Id
     */
    fun get(taskID: Int): TaskEntity? {

        var taskEntity: TaskEntity? = null

        try {
            val cursor: Cursor
            val db = this.mUltraTaskDataBaseHelper.readableDatabase

            // Colunas que serão retornadas
            val projection = arrayOf(
                DataBaseConstants.TASK.COLUMNS.USERID
                , DataBaseConstants.TASK.COLUMNS.DESCRIPTION
                , DataBaseConstants.TASK.COLUMNS.DUEDATE
                , DataBaseConstants.TASK.COLUMNS.PRIORITYID
                , DataBaseConstants.TASK.COLUMNS.COMPLETE
            )

            // Filtro
            val selection = "${DataBaseConstants.TASK.COLUMNS.ID} = ?"
            val selectionArgs = arrayOf(taskID.toString())

            // Carrega usuário
            cursor = db.query(
                DataBaseConstants.TASK.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            // Verifica se existe retorno
            if (cursor.count > 0) {
                cursor.moveToFirst()

                val userId =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.USERID))
                val description =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.DESCRIPTION))
                val dueDate =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.DUEDATE))
                val priorityId =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.PRIORITYID))
                val complete =
                    (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.COMPLETE)) == 1)

                // Atribui valor a variável do usuário
                taskEntity = TaskEntity(taskID, userId, priorityId, description, dueDate, complete)
            }

            // Fecha cursor
            cursor.close()

        } catch (e: Exception) {
            return taskEntity
        }

        // Retorno objeto com dados
        return taskEntity
    }

    /**
     * Carrega lista de tarefas
     */
    fun getList(filter: Int, userId: Int): MutableList<TaskEntity> {
        val list = mutableListOf<TaskEntity>()

        try {
            val cursor: Cursor
            val db = this.mUltraTaskDataBaseHelper.readableDatabase

            // Lista de taerfas filtradas de acordo com parâmetro
            cursor = db.rawQuery(
                "select * from ${DataBaseConstants.TASK.TABLE_NAME} WHERE " +
                        "${DataBaseConstants.TASK.COLUMNS.COMPLETE} = $filter" +
                        " AND ${DataBaseConstants.TASK.COLUMNS.USERID} = $userId", null
            )
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val taskID =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.ID))
                    val usuId =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.USERID))
                    val description = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            DataBaseConstants.TASK.COLUMNS.DESCRIPTION
                        )
                    )
                    val dueDate =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.DUEDATE))
                    val priorityId =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.PRIORITYID))
                    val complete =
                        (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.TASK.COLUMNS.COMPLETE)) == 1)

                    // Adiciona item a lista
                    list.add(TaskEntity(taskID, usuId, priorityId, description, dueDate, complete))
                }
            }

            // Fecha cursor
            cursor.close()

        } catch (e: Exception) {
            return list
        }

        // Retorno objeto com dados
        return list
    }

    /**
     * Faz a inserção da tarefa
     * */
    fun insert(task: TaskEntity) {
        try {

            // Para fazer escrita de dados
            val db = mUltraTaskDataBaseHelper.writableDatabase

            // Faz a conversão do boolean para inteiro
            val complete: Int = if (task.complete) 1 else 0

            val insertValues = ContentValues()
            insertValues.put(DataBaseConstants.TASK.COLUMNS.USERID, task.userId)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.PRIORITYID, task.priorityId)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.DESCRIPTION, task.description)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.DUEDATE, task.dueDate)
            insertValues.put(DataBaseConstants.TASK.COLUMNS.COMPLETE, complete)

            // Faz a inserção
            db.insert(DataBaseConstants.TASK.TABLE_NAME, null, insertValues)

        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Faz a atualização da tarefa
     * */
    fun update(task: TaskEntity) {
        try {

            // Para fazer escrita de dados
            val db = mUltraTaskDataBaseHelper.writableDatabase

            // Faz a conversão do boolean para inteiro
            val complete: Int = if (task.complete) 1 else 0

            val updateValues = ContentValues()
            updateValues.put(DataBaseConstants.TASK.COLUMNS.USERID, task.userId)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.DESCRIPTION, task.description)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.DUEDATE, task.dueDate)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.PRIORITYID, task.priorityId)
            updateValues.put(DataBaseConstants.TASK.COLUMNS.COMPLETE, complete)

            // Critério de seleção
            val selection = DataBaseConstants.TASK.COLUMNS.ID + " = ?"
            val selectionArgs = arrayOf(task.id.toString())

            // Executa
            db.update(DataBaseConstants.TASK.TABLE_NAME, updateValues, selection, selectionArgs)

        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Faz a remoção da tarefa
     * */
    fun delete(id: Int) {
        try {

            // Para fazer escrita de dados
            val db = mUltraTaskDataBaseHelper.writableDatabase

            val whereClause = "${DataBaseConstants.TASK.COLUMNS.ID} = ?"
            val whereArgs = arrayOf(id.toString())
            db.delete(DataBaseConstants.TASK.TABLE_NAME, whereClause, whereArgs)

            // Linha única
            // db.delete(DataBaseConstants.TASK.TABLE_NAME, "${DataBaseConstants.TASK.COLUMNS.ID} = $id", null)

        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Atualiza tarefa com completa ou pendente
     * */
    fun complete(id: Int, complete: Boolean) {
        val task: TaskEntity? = get(id)
        if (task != null) {
            task.complete = complete
            update(task)
        }
    }

}