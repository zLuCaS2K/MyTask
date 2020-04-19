package com.lucasprojects.mytask.repository.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.lucasprojects.mytask.constants.DataBaseConstants
import com.lucasprojects.mytask.constants.TaskConstants

class TaskDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION: Int = 1
        private const val DATABASE_NAME: String = "Tasks.db"
    }

    // Criação da tabela de usuário
    private val createTableUser = (
            "CREATE TABLE " + DataBaseConstants.USER.TABLE_NAME + " ("
                    + DataBaseConstants.USER.COLUMNS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DataBaseConstants.USER.COLUMNS.NAME + " TEXT, "
                    + DataBaseConstants.USER.COLUMNS.PASSWORD + " TEXT);")

    // Criação da tabela de categoria
    private val createTableProperty = """
        CREATE TABLE ${DataBaseConstants.PRIORITY.TABLE_NAME}
        (${DataBaseConstants.PRIORITY.COLUMNS.ID} INTEGER PRIMARY KEY,
        ${DataBaseConstants.PRIORITY.COLUMNS.DESCRIPTION} TEXT); """

    // Criação da tabela de tarefas
    private val createTableTask = (
            "CREATE TABLE " + DataBaseConstants.TASK.TABLE_NAME + " ("
                    + DataBaseConstants.TASK.COLUMNS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DataBaseConstants.TASK.COLUMNS.USERID + " INTEGER, "
                    + DataBaseConstants.TASK.COLUMNS.PRIORITYID + " INTEGER, "
                    + DataBaseConstants.TASK.COLUMNS.NAME + " TEXT, "
                    + DataBaseConstants.TASK.COLUMNS.TEXT + " TEXT, "
                    + DataBaseConstants.TASK.COLUMNS.DUEDATE + " TEXT, "
                    + DataBaseConstants.TASK.COLUMNS.COMPLETE + " INTEGER);")

    // Populando dados de prioridades
    private val inserPriorities = ("INSERT INTO ${DataBaseConstants.PRIORITY.TABLE_NAME}"
            + " VALUES (1, '${TaskConstants.PRIORITIES.LOW}')" +
            ", (2, '${TaskConstants.PRIORITIES.MEDIUM}')" +
            ", (3, '${TaskConstants.PRIORITIES.HIGH}')" +
            ", (4, '${TaskConstants.PRIORITIES.CRITICAL}')")

    // Remoção de tabelas
    //private val dropTableUser = "DROP TABLE IF EXISTS " + DataBaseConstants.USER.TABLE_NAME
    //private val dropTablePriority = "DROP TABLE IF EXISTS " + DataBaseConstants.PRIORITY.TABLE_NAME
    //private val dropTableTask = "DROP TABLE IF EXISTS " + DataBaseConstants.TASK.TABLE_NAME

    // Criação das tabelas
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        with(sqLiteDatabase){
            execSQL(createTableUser)
            execSQL(createTableProperty)
            execSQL(createTableTask)
            execSQL(inserPriorities)
        }
    }

    /** Atualização do banco de dados */
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        /** Remover todas as tabelas
            sqLiteDatabase.execSQL(dropTableUser)
            sqLiteDatabase.execSQL(dropTablePriority)
            sqLiteDatabase.execSQL(dropTableTask) */

        // Fazer a criação novamente
        // onCreate(sqLiteDatabase)

        // Tratamento de versões antigas
        // newVersion -> 3
        /*when (oldVersion){
            1 -> {
                // Lógica para atualizar da versão 1 para a 2 e da 2 para a 3
            }
            2 -> {
                // Lógica para atualizar da versão 2 para a 3
            }
        }*/
    }
}