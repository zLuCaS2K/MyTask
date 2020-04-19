package com.lucasprojects.mytask.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.adapter.IntroSlideAdapter
import com.lucasprojects.mytask.entities.IntroSlide
import com.lucasprojects.mytask.util.PreferenceManager
import kotlinx.android.synthetic.main.activity_slide.*

class SlideActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mPreferenceManager: PreferenceManager
    private lateinit var introSlideAdapter: IntroSlideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        mPreferenceManager = PreferenceManager(this)
        introSlideAdapter = setListAdapter()
        btnNext.setOnClickListener(this)
        textSkipIntro.setOnClickListener(this)
        if (mPreferenceManager.isFirstRun()) {
            showSlides()
            setupIndicators()
            setCurrentIndicator(0)
            introSliderViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }
            })
        } else {
            goToOpening()
        }
    }

    /** Eventos de click */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNext -> {
                if (introSliderViewPager.currentItem + 1 < introSlideAdapter.itemCount) {
                    introSliderViewPager.currentItem += 1
                } else {
                    goToOpening()
                }
            }
            R.id.textSkipIntro -> {
                goToOpening()
            }
        }
    }

    /** Método responsável por fazer a configuração dos indicadores */
    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)
        val layoutParans = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParans.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(this)
            indicators[i].apply {
                this?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive))
                this?.layoutParams = layoutParans }
            indicatorsContainer.addView(indicators[i])
        }
    }

    /** Método responsável por definir os indicadores */
    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive))
            }
        }
    }

    /** Método responsável por definir o adapter */
    private fun showSlides() {
        mPreferenceManager.setFirstRun()
        introSliderViewPager.adapter = introSlideAdapter
    }

    /** Método para direcionar direto para a OpeningActivity */
    private fun goToOpening() {
        startActivity(Intent(this, OpeningActivity::class.java))
        finish()
    }

    /** Lista de Slides para ser exibido */
    private fun setListAdapter() = IntroSlideAdapter(
        listOf(
            IntroSlide(
                this.resources.getString(R.string.welcome),
                this.resources.getString(R.string.welcome_text),
                R.raw.welcome
            ),
            IntroSlide(
                this.resources.getString(R.string.task_scheduler),
                this.resources.getString(R.string.task_scheduler_text),
                R.raw.task_man
            ),
            IntroSlide(
                this.resources.getString(R.string.license_code),
                this.resources.getString(R.string.license_code_text),
                R.raw.license
            )
        )
    )
}
