package com.lucasprojects.mytask.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.activities.TaskFormActivity
import com.lucasprojects.mytask.adapter.TaskListAdapter
import com.lucasprojects.mytask.business.TaskBusiness
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.entities.listenner.OnTaskListFragmentInteractionListenner

class TaskListFragment : Fragment(), View.OnClickListener {

    private var mFilter: Int = 0
    private lateinit var mContext: Context
    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mOnTaskListFragmentInteractionListenner: OnTaskListFragmentInteractionListenner
    private lateinit var mRecyclerTaskList: RecyclerView

    companion object {
        fun newInstance(filter: Int): TaskListFragment {
            /** Instãncia do Fragment */
            val fragment = TaskListFragment()
            /**Adicionando parâmetros */
            val bundle = Bundle()
            bundle.putInt(TaskConstants.TASKFILTER.FILTERKEY, filter)
            fragment.arguments = bundle
            /**Retorna o Fragment */
            return fragment
        }
    }

    /** Metodo de criação do Fragment */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Verificação de qual filtro foi passado para a listagem */
        if (arguments != null) {
            val bundle = arguments
            mFilter = bundle!!.getInt(TaskConstants.TASKFILTER.FILTERKEY,0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)

        // Incializa as variáveis
        mContext = rootView.context
        mTaskBusiness = TaskBusiness(mContext)

        // Necessário buscar os elementos de interface através do findViewById. Não funciona Kotlin-Extensions
        rootView.findViewById<FloatingActionButton>(R.id.fabAddTask).setOnClickListener(this)

        // Inicializa listener
        createInteractionListener()

        // 1 - Obter a recyclerView
        mRecyclerTaskList = rootView.findViewById(R.id.recyclerTaskList)

        // 2 - Definir adapter passando listagem de itens
        val taskListAdapter =
            TaskListAdapter(mutableListOf(), mOnTaskListFragmentInteractionListenner)
        mRecyclerTaskList.adapter = taskListAdapter

        // 3 - Definir um layout
        mRecyclerTaskList.layoutManager = LinearLayoutManager(mContext)

        return rootView
    }

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

        // Carrega lista de tarefas
        val listTaskEntity: MutableList<TaskEntity> = mTaskBusiness.getList(mFilter)

        // Inicializa o adapter com registros atualizados
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
        }
    }
}
