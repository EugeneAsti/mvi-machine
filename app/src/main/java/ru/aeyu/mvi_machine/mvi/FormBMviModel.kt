package ru.aeyu.mvi_machine.mvi

import ru.aeyu.mvi_machine.mvi.actions.FormBActions
import ru.aeyu.mvi_machine.mvi.states.FormBViewState
import ru.aeyu.mvi_machine.mvi_machine.MviModel

class FormBMviModel(
    reducer: FormBReducer
) : MviModel<FormBActions, FormBViewState>(reducer, FormBViewState(false))