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
import java.util.*

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var mPriorityEntityList: MutableList<PriorityEntity> = mutableListOf()
    private var mPriorityEntityListId: MutableList<Int> = mutableListOf()
    private var mTaskId: Int = 0
    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mPriorityBusiness: PriorityBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mTaskBusiness = TaskBusiness(this)
        mPriorityBusiness = PriorityBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)
        setListeners()
        loadPriorities()
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
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        val stringDate = dateFormat.format(calendar.time)
        btnPickerDate.text = stringDate
    }

    /** Carregamento das prioridades */
    private fun loadPriorities() {
        mPriorityEntityList = mPriorityBusiness.getList()
        val listPriorities = mPriorityEntityList.map { it.description }
        mPriorityEntityListId = mPriorityEntityList.map { it.id }.toMutableList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listPriorities)
        spinnerPriority.adapter = adapter
    }

    /** Salvando tarefa */
    private fun handleSave() {
        try {
            val userId: Int = mSecurityPreferences.getSharedStored(TaskConstants.KEY.USER_ID).toInt()
            val name: String = editTaskName.text.toString()
            val text: String = editTaskText.text.toString()
            val priorityId = mPriorityEntityListId[spinnerPriority.selectedItemPosition]
            val dueDate: String = btnPickerDate.text.toString()
            val complete: Boolean = checkboxComplete.isChecked
            val task = TaskEntity(mTaskId, userId, priorityId, name, text, dueDate, complete)
            if (mTaskId == 0) {
                mTaskBusiness.insert(task)
                Toast.makeText(this, R.string.add_task_sucess, Toast.LENGTH_LONG).show()
            } else {
                mTaskBusiness.update(task)
                Toast.makeText(this, R.string.update_task_sucess, Toast.LENGTH_LONG).show()
            }
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
            if (mTaskId != 0) {
                btnSaveTask.setText(R.string.update_task_button)
                val task = mTaskBusiness.get(mTaskId)
                editTaskName.setText(task?.name)
                editTaskText.setText(task?.text)
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
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }

    /** Eventos de click */
    private fun setListeners() {
        btnSaveTask.setOnClickListener(this)
        btnPickerDate.setOnClickListener(this)
    }
}
