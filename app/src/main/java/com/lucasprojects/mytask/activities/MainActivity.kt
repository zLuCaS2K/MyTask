package com.lucasprojects.mytask.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.business.PriorityBusiness
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.fragments.TaskListFragment
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants
import com.lucasprojects.mytask.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private lateinit var mPriorityBusiness: PriorityBusiness

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
        imageMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        /** Removendo a cor default da NavigationDrawer */
        navigationView.itemIconTintList = null
        /** Localizando o NavController */
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        /** Configurando a navegação do app */
        NavigationUI.setupWithNavController(navigationView, navController)
        /** Execução com a navegação baseada no destino */
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.nav_Done){
                val fragment: Fragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.TODO)
                val fragmentManager = supportFragmentManager
                fragmentManager.beginTransaction().replace(R.id.navHostFragment, fragment).commit()
            }
            Toast.makeText(this, destination.label, Toast.LENGTH_SHORT).show()
        }
    }
    /** Inicialização do Cache de prioridades */
    private fun loadCachePriority() {
        PriorityCacheConstants.setCache(mPriorityBusiness.getList())
    }
}
