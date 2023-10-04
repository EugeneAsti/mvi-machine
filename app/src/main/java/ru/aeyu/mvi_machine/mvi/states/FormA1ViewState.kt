package ru.aeyu.mvi_machine.mvi.states

import ru.aeyu.mvi_machine.mvi_machine.ViewState
import ru.aeyu.mvi_machine.mvi.models.FormADataObject

data class FormA1ViewState(
    val isLoading: Boolean,
    val dataObject: FormADataObject?
) : ViewState
