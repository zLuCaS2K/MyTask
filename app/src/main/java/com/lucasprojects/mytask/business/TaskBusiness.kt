package com.lucasprojects.mytask.business

import android.content.Context
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.repository.TaskRepository
import com.lucasprojects.mytask.util.SecurityPreferences
import com.lucasprojects.mytask.util.ValidationException

class TaskBusiness(val context: Context) {
    private val mTaskRepository: TaskRepository = TaskRepository.getInstance(context)
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)

    /** Retorna entidade de task */
    fun get(id: Int): TaskEntity? = mTaskRepository.get(id)

    /** Retorna lista de tarefas */
    fun getList(filter: Int): MutableList<TaskEntity> {

        // Obtém o Id do usuário
        val userId: Int = mSecurityPreferences.getSharedStored(TaskConstants.KEY.USER_ID).toInt()

        // Faz a listagem de tarefas com filtros
        return mTaskRepository.getList(filter, userId)
    }

    /** Faz a inserção da tarefa */
    fun insert(task: TaskEntity) {

        try {
            // Faz a validação dos campos
            if (task.description == "" || task.dueDate == "" || task.priorityId == 0) {
                throw ValidationException(context.getString(R.string.all_camps))
            }

            // Faz a inserção da tarefa
            mTaskRepository.insert(task)
        } catch (e: Exception) {
            throw e
        }
    }

    /** Faz a atualização da task */
    fun update(task: TaskEntity) {

        try {
            // Faz a validação dos campos
            if (task.description == "" || task.dueDate == "" || task.priorityId == 0) {
                throw ValidationException(context.getString(R.string.all_camps))
            }

            // Faz a atualização da tarefa
            mTaskRepository.update(task)
        } catch (e: Exception) {
            throw e
        }
    }

    /** Faz a remoção da task */
    fun delete(id: Int) = mTaskRepository.delete(id)

    /** Faz a atualização da task como completa ou pendente */
    fun complete(id: Int, complete: Boolean) = mTaskRepository.complete(id, complete)
}