package ru.aeyu.mvi_machine.mvi.actions

import ru.aeyu.mvi_machine.mvi.models.FormA1DataObject
import ru.aeyu.mvi_machine.mvi_machine.MviActions

sealed class FormAActions : MviActions {
    data class OnGetDataClicked(val dataId: Int) : FormAActions()
    object OnNextFragment : FormAActions()
    object OnLoad : FormAActions()
    object OnLoadEnd : FormAActions()
    data class OnGetDataSuccess(val newData: FormA1DataObject) : FormAActions()
    data class OnFailure(val throwable: Throwable) : FormAActions()
}
