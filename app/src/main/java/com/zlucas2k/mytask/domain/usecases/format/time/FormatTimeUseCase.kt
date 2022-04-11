package com.zlucas2k.mytask.domain.usecases.format.time

interface FormatTimeUseCase {

    operator fun invoke(hour: Int, minute: Int): String
}