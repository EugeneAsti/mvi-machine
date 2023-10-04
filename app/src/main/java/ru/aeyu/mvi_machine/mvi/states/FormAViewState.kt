package ru.aeyu.mvi_machine.mvi.states

import ru.aeyu.mvi_machine.mvi_machine.ViewState
import ru.aeyu.mvi_machine.mvi.models.FormA1DataObject

data class FormAViewState(
    val isLoading: Boolean,
    val dataObject: FormA1DataObject?
) : ViewState
