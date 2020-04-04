package com.lucasprojects.mytask.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
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
    private val introSlideAdapter = IntroSlideAdapter(
        listOf(
            IntroSlide(
                "Bem-Vindo",
                "O MyTask é um app totalmente de código aberto, você pode modificá-lo quanto quiser!",
                R.raw.welcome
            ),
            IntroSlide(
                "Agendamento de Tarefas",
                "O agendamento ainda está em processo de desenvolvimento, por enquanto o app está somente anotando!",
                R.raw.task_man
            ),
            IntroSlide(
                "Licenças de Código",
                "Todos os ícones, animações, imagens, APIs e bibliotecas usadas, podem ser encontradas na barra de navegação, no item Sobre!",
                R.raw.license
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        mPreferenceManager = PreferenceManager(this)
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

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)
        val layoutParans = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParans.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(this)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParans
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

    private fun showSlides() {
        mPreferenceManager.setFirstRun()
        introSliderViewPager.adapter = introSlideAdapter
    }

    private fun goToOpening() {
        startActivity(Intent(this, OpeningActivity::class.java))
        finish()
    }
}
