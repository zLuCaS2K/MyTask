package com.zlucas2k.mytask.domain.usecases.validate.input

import com.zlucas2k.mytask.common.exceptions.TaskException

class ValidateInputsUseCaseImpl : ValidateInputsUseCase {

    override fun invoke(vararg inputs: String) {
        inputs.forEach { input ->
            if (input.isBlank()) {
                throw TaskException("")
            }
        }
    }
}