package com.zlucas2k.mytask.domain.usecases.validate.input

import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.providers.StringResourceProvider
import javax.inject.Inject

class ValidateInputsUseCaseImpl @Inject constructor(
    private val stringResourceProvider: StringResourceProvider
) : ValidateInputsUseCase {

    override fun invoke(vararg inputs: String) {
        inputs.forEach { input ->
            if (input.isBlank()) {
                throw TaskException(stringResourceProvider.getString(R.string.enter_all_fields))
            }
        }
    }
}