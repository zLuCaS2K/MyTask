package com.zlucas2k.mytask.domain.usecases.format.date

import java.util.Locale
import java.text.SimpleDateFormat

class FormatDateUseCaseImpl : FormatDateUseCase {

    private val _formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    override fun invoke(date: String): String {
        return _formatter.format(((_formatter.parse(date))))
    }
}