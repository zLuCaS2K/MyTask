package com.lucasprojects.mytask.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.business.PriorityBusiness
import com.lucasprojects.mytask.business.TaskBusiness
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.PriorityEntity
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.util.SecurityPreferences
import com.lucasprojects.mytask.util.ValidationException
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.SimpleDateFormat

class TaskFormActivity : AppCompatActivity(), View.OnClickListener,
    DatePickerDialog.OnDateSetListener {

    /** Inicialização do SimpleDateFormat */
    private var SIMPLE_DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy")
    /** Lista de PriorityEntity */
    private var mPriorityEntityList: MutableList<PriorityEntity> = mutableListOf()
    /** Lista de IDs de prioridades */
    private var mPriorityEntityListId: MutableList<Int> = mutableListOf()
    /** Váriavel global do ID de uma prioridade */
    private var mTaskId: Int = 0
    /** Váriaveis da Camada Business */
    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mPriorityBusiness: PriorityBusiness
    /** Váriaveis do SharedPreferences */
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        /** Instancia das Classes Business */
        mTaskBusiness = TaskBusiness(this)
        mPriorityBusiness = PriorityBusiness(this)
        /** Instancia da Classe Security Preferences */
        mSecurityPreferences = SecurityPreferences(this)
        /** Definindo os listenners do itens */
        setListeners()
        /** Carregamento dos valores de prioridades */
        loadPriorities()
        /** Carregamento dos dados passados para a activity */
        loadDataFromActivity()
    }

    /** Eventos de click */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnSaveTask -> {
                handleSave()
            }
            R.id.btnPickerDate -> {
                showDateDialog()
            }
        }
    }

    /** Tratamento do evento de seleção de data */
    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = java.util.Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        val stringDate = SIMPLE_DATE_FORMAT.format(calendar.time)
        btnPickerDate.text = stringDate
    }

    /** Carregamento das prioridades */
    private fun loadPriorities() {

        /** Lista de prioridades do banco de dados local */
        mPriorityEntityList = mPriorityBusiness.getList()

        /** Cria uma lista de strings para preenchimento do spinner */
        val listPriorities = mPriorityEntityList.map { it.description }

        /** Mapeia a lista de IDs das prioridades */
        mPriorityEntityListId = mPriorityEntityList.map { it.id }.toMutableList()

        /** Criando adapter e usando no elemento */
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listPriorities
        )
        spinnerPriority.adapter = adapter
    }

    /** Salvando tarefa */
    private fun handleSave() {

        try {
            val userId: Int = mSecurityPreferences.getSharedStored(TaskConstants.KEY.USER_ID).toInt()
            val description: String = editDescription.text.toString()
            val priorityId = mPriorityEntityListId[spinnerPriority.selectedItemPosition]
            val dueDate: String = btnPickerDate.text.toString()
            val complete: Boolean = checkboxComplete.isChecked

            /** Inicializa entidade TaskEntity */
            val task = TaskEntity(mTaskId, userId, priorityId, description, dueDate, complete)

            /** Inserção de tarefa */
            if (mTaskId == 0) {
                mTaskBusiness.insert(task)
                Toast.makeText(this, R.string.add_task_sucess, Toast.LENGTH_LONG).show()
            } else {
                mTaskBusiness.update(task)
                Toast.makeText(this, R.string.update_task_sucess, Toast.LENGTH_LONG).show()
            }

            /** Finaliza formulário */
            finish()

        } catch (e: ValidationException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    /** Obtendo o valor do index de prioridade */
    private fun getIndex(priorityId: Int): Int {
        var index = 0
        for (i in 0..mPriorityEntityList.size) {
            if (mPriorityEntityList[i].id == priorityId) {
                index = i
                break
            }
        }
        return index
    }

    /** Carregamento dos dados para a edição */
    private fun loadDataFromActivity() {
        val bundle = intent.extras
        if (bundle != null) {
            this.mTaskId = bundle.getInt(TaskConstants.BUNDLE.TASKID, 0)

            /** Carrega tarefa */
            if (mTaskId != 0) {
                btnSaveTask.setText(R.string.update_task_button)

                /** Carrega tarefa */
                val task = mTaskBusiness.get(mTaskId)

                /** Atribui valores as propriedades */
                editDescription.setText(task?.description)
                btnPickerDate.text = task?.dueDate
                if (task != null) {
                    checkboxComplete.isChecked = task.complete
                    spinnerPriority.setSelection(getIndex(task.priorityId))
                }
            }
        }
    }

    /** Exibindo o datepicker de seleção de data */
    private fun showDateDialog() {
        val c = java.util.Calendar.getInstance()
        val year = c.get(java.util.Calendar.YEAR)
        val month = c.get(java.util.Calendar.MONTH)
        val day = c.get(java.util.Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, this, year, month, day).show()
    }

    /** Eventos de click */
    private fun setListeners() {
        btnSaveTask.setOnClickListener(this)
        btnPickerDate.setOnClickListener(this)
    }
}
