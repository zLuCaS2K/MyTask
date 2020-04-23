package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.business.PriorityBusiness
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants
import com.lucasprojects.mytask.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mPriorityBusiness: PriorityBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPriorityBusiness = PriorityBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)
        loadCachePriority()

        imageMenuOne.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        imageMenuTwo.setOnClickListener {
            handleLogout()
        }

        val itemLogout = navigationView.menu.findItem(R.id.nav_Logout)
        itemLogout.setOnMenuItemClickListener {
            handleLogout()
            true
        }

        setDateUserHeader()
        setDateUserTitle()

        navigationView.itemIconTintList = null
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigationView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_Done -> titleListTask.text = getString(R.string.tasks_done)
                R.id.nav_Todo -> titleListTask.text = getString(R.string.tasks_todo)
                R.id.nav_About -> titleListTask.text = getString(R.string.about)
            }
        }
    }

    /** Inicialização do Cache de prioridades */
    private fun loadCachePriority() {
        PriorityCacheConstants.setCache(mPriorityBusiness.getList())
    }

    /** Metodo de fazer logout do usuário */
    private fun handleLogout() {
        /** Limpando os dados do usuário do SharedPreferences */
        mSecurityPreferences.removeSharedStored(TaskConstants.KEY.USER_ID)
        mSecurityPreferences.removeSharedStored(TaskConstants.KEY.USER_NAME)
        /** Chamando a LoginActivity */
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    /** Metodo responsável por definir o texto da Header da NavigationView com os dados do usuário */
    private fun setDateUserHeader() {
        val headerView = navigationView.getHeaderView(0)
        val textUserName = headerView.findViewById<TextView>(R.id.textUserName)
        textUserName.text = mSecurityPreferences.getSharedStored(TaskConstants.KEY.USER_NAME)
    }

    /** Metodo responsável por definir o texto da toolbar com o nome do usuário logado */
    private fun setDateUserTitle() {
        textTitle.text = mSecurityPreferences.getSharedStored(TaskConstants.KEY.USER_NAME)
    }
}
