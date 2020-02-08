package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintro.AppIntro2Fragment
import com.github.paolorotolo.appintro.model.SliderPagerBuilder
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.util.PreferenceManager

class SlideActivity : AppIntro2() {

    private lateinit var mPreferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPreferenceManager = PreferenceManager(this)
        if (mPreferenceManager.isFirstRun()) {
            showIntroSlides()
        } else {
            goToMain()
        }
    }

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

        addSlide(AppIntro2Fragment.newInstance(pageOne))
        addSlide(AppIntro2Fragment.newInstance(pageTwo))
    }

    private fun goToMain() {
        startActivity(Intent(this, OpeningActivity::class.java))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        goToMain()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        goToMain()
    }
}
