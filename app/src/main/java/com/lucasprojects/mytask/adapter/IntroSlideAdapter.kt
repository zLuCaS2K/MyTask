package com.lucasprojects.mytask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.entities.IntroSlide

class IntroSlideAdapter(private val listIntroSlide: List<IntroSlide>) : RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>() {

    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textTitle = view.findViewById(R.id.textTitleContainer) as TextView
        private val textDescription = view.findViewById(R.id.textDescriptionContainer) as TextView
        private val imageIcon = view.findViewById(R.id.imageSlideIcon) as LottieAnimationView

        fun bindData(introSlide: IntroSlide) {
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setAnimation(introSlide.icon)
            imageIcon.playAnimation()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.slide_item_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listIntroSlide.size
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bindData(listIntroSlide[position])
    }
}