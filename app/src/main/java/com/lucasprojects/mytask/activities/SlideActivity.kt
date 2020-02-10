package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintro.AppIntro2Fragment
import com.github.paolorotolo.appintro.model.SliderPagerBuilder
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.util.PreferenceManager

/**
 * A SlideActivity é a activity responsável por mostrar slides de apresentação do app ao usuário,
 * essa activity só será chamada uma única vez, ou seja apenas na primeira abertura do app.
 * */

class SlideActivity : AppIntro2() {

    /** Váriaveis do SharedPreferences */
    private lateinit var mPreferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** Instancia da Classe PreferenceManager */
        mPreferenceManager = PreferenceManager(this)
        /** Verificação se é a primeira vez que o app está sendo aberto */
        if (mPreferenceManager.isFirstRun()) {
            showIntroSlides()
        } else {
            goToMain()
        }
    }

    /** Metodo responsável por mostrar os slides de apresentação */
    private fun showIntroSlides() {
        mPreferenceManager.setFirstRun()
        val pageOne = SliderPagerBuilder()
            .title(getString(R.string.slide_welcome))
            .description(getString(R.string.slide_welcome_description))
            .imageDrawable(R.mipmap.ic_launcher)
            .bgColor(R.color.colorBlack)
            .build()
        val pageTwo = SliderPagerBuilder()
            .title(getString(R.string.slide_welcome))
            .description(getString(R.string.slide_welcome_description))
            .imageDrawable(R.mipmap.ic_launcher)
            .bgColor(R.color.colorBlack)
            .build()

        /** Adicionando Slides ao App */
        addSlide(AppIntro2Fragment.newInstance(pageOne))
        addSlide(AppIntro2Fragment.newInstance(pageTwo))
    }

    /** Metodo que chama a OpeningAcitivity */
    private fun goToMain() {
        startActivity(Intent(this, OpeningActivity::class.java))
    }

    /** Metodo que chama a OpeningAcitivity, caso o usuário aperte no botão de pular */
    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        goToMain()
    }

    /** Metodo que chama a OpeningAcitivity, caso o usuário aperte no botão de próximo */
    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        goToMain()
    }
}
