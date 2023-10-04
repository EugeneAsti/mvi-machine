package ru.aeyu.mvi_machine.mvi.actions.internal

import ru.aeyu.mvi_machine.mvi.models.FormADataObject
import ru.aeyu.mvi_machine.mvi_machine.MviInternalIntent

sealed class FormA1InternalActions : MviInternalIntent {
    object OnLoad : FormA1InternalActions()
    object OnLoadEnd : FormA1InternalActions()
    data class OnGetDataSuccess(val newData: FormADataObject) : FormA1InternalActions()
    data class OnFailure(val throwable: Throwable) : FormA1InternalActions()
}
