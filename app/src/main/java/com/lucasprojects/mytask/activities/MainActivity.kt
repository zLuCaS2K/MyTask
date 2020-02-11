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

/**
 * A MainActivity principal do app, ela é reponsável pela navegação, e é onde o carregamento das
 * PriorityCache irá ser feito.
 * */

class MainActivity : AppCompatActivity() {

    /** Váriaveis da Camada Business */
    private lateinit var mPriorityBusiness: PriorityBusiness
    /** Váriaveis do SharedPreferences */
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /** Instancia da Classe Business */
        mPriorityBusiness = PriorityBusiness(this)
        /** Instancia da Classe Security Preferences */
        mSecurityPreferences = SecurityPreferences(this)
        /** Inicializa o Cache de Priority */
        loadCachePriority()
        /** Abrir NavigationDrawer */
        imageMenuOne.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        /** Evento de logout do app */
        val itemLogout = navigationView.menu.findItem(R.id.nav_Logout)
        itemLogout.setOnMenuItemClickListener {
            handleLogout()
            true
        }
        /** Definindo texto da Header da NavigationView */
        setDateUserHeader()
        /** Definindo texto da Title da Toolbar */
        setDateUserTitle()
        /** Removendo a cor default da NavigationDrawer */
        navigationView.itemIconTintList = null
        /** Localizando o NavController */
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        /** Configurando a navegação do app */
        NavigationUI.setupWithNavController(navigationView, navController)
        /** Execução com a navegação baseada no destino */
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.nav_Done -> titleListTask.text = getString(R.string.tasks_done)
                R.id.nav_Todo -> titleListTask.text = getString(R.string.tasks_todo)
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
        mSecurityPreferences.removeSharedStored(TaskConstants.KEY.USER_EMAIL)
        /** Chamando a LoginActivity */
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    /** Metodo responsável por difinir o texto da Header da NavigationView com os dados do usuário */
    private fun setDateUserHeader() {
        /** Obtendo o HeaderView */
        val headerView = navigationView.getHeaderView(0)
        /** Recuperando views do Header da NavigationView */
        val textUserName = headerView.findViewById<TextView>(R.id.textUserName)
        val textUserEmail = headerView.findViewById<TextView>(R.id.textUserEmail)
        /** Definindo o texto com os dados do usuário logado */
        textUserName.text = mSecurityPreferences.getSharedStored(TaskConstants.KEY.USER_NAME)
        textUserEmail.text = mSecurityPreferences.getSharedStored(TaskConstants.KEY.USER_EMAIL)
    }

    /** Metodo responsável por difinir o texto da toolbar com o nome do usuário logado */
    private fun setDateUserTitle() {
        /** Definindo o texto com os dados do usuário logado */
        textTitle.text = mSecurityPreferences.getSharedStored(TaskConstants.KEY.USER_NAME)
    }
}
