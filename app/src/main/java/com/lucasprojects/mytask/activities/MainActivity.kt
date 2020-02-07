package com.lucasprojects.mytask.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.lucasprojects.mytask.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            Toast.makeText(this, destination.label, Toast.LENGTH_SHORT).show()
        }
    }
}
