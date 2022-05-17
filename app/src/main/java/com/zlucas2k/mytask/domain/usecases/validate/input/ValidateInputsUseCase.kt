package com.zlucas2k.mytask.domain.usecases.validate.input

interface ValidateInputsUseCase {

    operator fun invoke(vararg inputs: String)
}