package ru.aeyu.mvi_machine.mvi

import ru.aeyu.mvi_machine.mvi.actions.internal.FormA1InternalActions
import ru.aeyu.mvi_machine.mvi.actions.view.FormA1UserActions
import ru.aeyu.mvi_machine.mvi.states.FormA1ViewState
import ru.aeyu.mvi_machine.mvi_machine.MviModel

class FormA1MviModel(
    reducer: FormA1Reducer
) : MviModel<FormA1UserActions, FormA1InternalActions, FormA1ViewState>(
    reducer,
    FormA1ViewState(false, null)
)