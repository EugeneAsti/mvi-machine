package ru.aeyu.mvi_machine.mvi

import ru.aeyu.mvi_machine.mvi.actions.FormAActions
import ru.aeyu.mvi_machine.mvi.states.FormAViewState
import ru.aeyu.mvi_machine.mvi_machine.MviModel

class FormAMviModel(
    reducer: FormAReducer
) : MviModel<FormAActions, FormAViewState>(reducer, FormAViewState(false, null))