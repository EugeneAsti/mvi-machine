package ru.aeyu.mvi_machine.mvi.actions

import ru.aeyu.mvi_machine.mvi.models.FormA1DataObject
import ru.aeyu.mvi_machine.mvi_machine.InternalIntent
import ru.aeyu.mvi_machine.mvi_machine.UserIntent

sealed class FormAIntent : UserIntent {
    data class OnGetDataClicked(val dataId: Int) : FormAIntent()
    object OnNextFragment : FormAIntent()
}

sealed class FormAInternalIntent : InternalIntent {
    object Idle : FormAInternalIntent()
    object OnLoad : FormAInternalIntent()
    object OnLoadEnd : FormAInternalIntent()
    data class OnGetDataSuccess(val newData: FormA1DataObject) : FormAInternalIntent()
    data class OnFailure(val throwable: Throwable) : FormAInternalIntent()
}
