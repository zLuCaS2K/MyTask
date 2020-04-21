package com.lucasprojects.mytask.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.activities.TaskFormActivity
import com.lucasprojects.mytask.adapter.TaskListAdapter
import com.lucasprojects.mytask.business.TaskBusiness
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.entities.listenner.OnTaskListFragmentInteractionListenner
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants
import com.lucasprojects.mytask.util.Utils

class TaskListFragment : Fragment(), View.OnClickListener {

    private var mFilter: Int = 0
    private lateinit var mContext: Context
    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mOnTaskListFragmentInteractionListenner: OnTaskListFragmentInteractionListenner
    private lateinit var mRecyclerTaskList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            val bundle = arguments
            mFilter = bundle!!.getInt(TaskConstants.TASKFILTER.FILTERKEY, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)

        mContext = rootView.context
        mTaskBusiness = TaskBusiness(mContext)
        rootView.findViewById<FloatingActionButton>(R.id.fabAddTask).setOnClickListener(this)

        createInteractionListener()
        mRecyclerTaskList = rootView.findViewById(R.id.recyclerTaskList)
        val taskListAdapter = TaskListAdapter(mutableListOf(), mOnTaskListFragmentInteractionListenner)
        with(mRecyclerTaskList) {
            adapter = taskListAdapter
            layoutManager = LinearLayoutManager(mContext)
        }
        return rootView
    }

    /** Metodo de onResume */
    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    /** Trata evento dos elementos */
    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.fabAddTask) {
            val intent = Intent(mContext, TaskFormActivity::class.java)
            startActivity(intent)
        }
    }

    /** Carregamento de tarefas */
    private fun loadTasks() {
        val listTaskEntity: MutableList<TaskEntity> = mTaskBusiness.getList(mFilter)
        mRecyclerTaskList.adapter = TaskListAdapter(listTaskEntity, mOnTaskListFragmentInteractionListenner)
    }

    /** Interação com a listagem de tarefas */
    private fun createInteractionListener() {
        mOnTaskListFragmentInteractionListenner = object : OnTaskListFragmentInteractionListenner {
            override fun onListClick(taskId: Int) {
                val bundle = Bundle()
                bundle.putInt(TaskConstants.BUNDLE.TASKID, taskId)

                val intent = Intent(mContext, TaskFormActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDeleteClick(taskId: Int) {
                mTaskBusiness.delete(taskId)
                Toast.makeText(mContext, getString(R.string.remove_task_sucess), Toast.LENGTH_LONG).show()
                loadTasks()
            }

            override fun onCompleteTaskClick(taskId: Int) {
                mTaskBusiness.complete(taskId, true)
                loadTasks()
            }

            override fun onUnCompleteTaskClick(taskId: Int) {
                mTaskBusiness.complete(taskId, false)
                loadTasks()
            }

            override fun onShowBottomSheetDialog(taskEntity: TaskEntity) {
                showBottomSheetDialog(taskEntity, this)
            }
        }
    }

    private fun showBottomSheetDialog(taskEntity: TaskEntity, listener: OnTaskListFragmentInteractionListenner) {
        val bottomSheetDialog = BottomSheetDialog(mContext, R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_sheet, bottomSheetDialog.findViewById(R.id.bottomSheetContainer))
        val bottomLottieAnimation = bottomSheetView.findViewById(R.id.bottomLottieAnimation) as LottieAnimationView
        val bottomNameTask = bottomSheetView.findViewById(R.id.bottomNameTask) as TextView
        val bottomDueDateTask = bottomSheetView.findViewById(R.id.bottomDueDateTask) as TextView
        val bottomPriorityTask = bottomSheetView.findViewById(R.id.bottomPriorityTask) as TextView
        val containerEditTask = bottomSheetView.findViewById(R.id.containerEditTask) as LinearLayout
        val containerDeleteTask = bottomSheetView.findViewById(R.id.containerDeleteTask) as LinearLayout
        val containerCompleteTask = bottomSheetView.findViewById(R.id.containerCompleteTask) as LinearLayout
        val btnCloseBottom = bottomSheetView.findViewById(R.id.btnCloseBottom) as Button

        with(taskEntity) {
            bottomNameTask.text = this.name
            bottomDueDateTask.text = this.dueDate
            bottomPriorityTask.text = PriorityCacheConstants.getPriorityDescription(this.priorityId)
        }

        Utils.setColorPriority(bottomPriorityTask)
        bottomLottieAnimation.playAnimation()

        containerEditTask.setOnClickListener {
            listener.onListClick(taskEntity.id)
            bottomSheetDialog.dismiss()
        }

        containerDeleteTask.setOnClickListener {
            showConfirmDialog(taskEntity, listener)
            bottomSheetDialog.dismiss()
        }

        containerCompleteTask.setOnClickListener {

        }

        btnCloseBottom.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    private fun showConfirmDialog(taskEntity: TaskEntity, listenner: OnTaskListFragmentInteractionListenner) {
        AlertDialog.Builder(mContext)
            .setTitle(R.string.task_remove_confirm)
            .setMessage("${mContext.resources.getString(R.string.task_remove_message)} ${taskEntity.name}?")
            .setPositiveButton(R.string.remove) { _, _ -> listenner.onDeleteClick(taskEntity.id) }
            .setNegativeButton(R.string.cancel, null).show()
    }
}
