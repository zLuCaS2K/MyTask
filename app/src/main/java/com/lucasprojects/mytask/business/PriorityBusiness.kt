package com.lucasprojects.mytask.business

import android.content.Context
import com.lucasprojects.mytask.entities.PriorityEntity
import com.lucasprojects.mytask.repository.PriorityRepository

class PriorityBusiness(context: Context) {

    private val mPriorityRepository: PriorityRepository = PriorityRepository.getInstance(context)

    fun getList(): MutableList<PriorityEntity> = mPriorityRepository.getList()
}