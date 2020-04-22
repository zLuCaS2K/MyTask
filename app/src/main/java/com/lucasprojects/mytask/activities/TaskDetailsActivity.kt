package com.lucasprojects.mytask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.business.TaskBusiness
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants
import kotlinx.android.synthetic.main.activity_task_details.*

class TaskDetailsActivity : AppCompatActivity() {

    private var mTaskId = 0
    private lateinit var mTaskBusiness: TaskBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)
        mTaskBusiness = TaskBusiness(this)
        loadDataTaskDetails()
    }

    private fun loadDataTaskDetails() {
        val bundle = intent.extras
        if (bundle != null) {
            mTaskId = bundle.getInt(TaskConstants.BUNDLE.TASKID, 0)
            if (mTaskId != 0) {
                val task = mTaskBusiness.get(mTaskId)
                with(task) {
                    textDetailsName.text = this?.name
                    textDetailsPriority.text = this?.priorityId?.let { PriorityCacheConstants.getPriorityDescription(it) }
                    textDetailsDueDate.text = this?.dueDate
                    textDetailsText.text = this?.text
                }
            }
        }
    }
}
