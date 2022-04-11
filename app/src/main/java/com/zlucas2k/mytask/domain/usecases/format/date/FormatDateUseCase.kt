package com.zlucas2k.mytask.domain.usecases.format.date

interface FormatDateUseCase {

    operator fun invoke(date: String): String
}