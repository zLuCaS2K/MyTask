package com.zlucas2k.mytask.domain.providers

interface StringResourceProvider {

    fun getString(stringResId: Int): String
}