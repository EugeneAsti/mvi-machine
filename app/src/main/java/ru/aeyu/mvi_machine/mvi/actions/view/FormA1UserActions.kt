package ru.aeyu.mvi_machine.mvi.actions.view

import ru.aeyu.mvi_machine.mvi_machine.MviUserIntent

sealed class FormA1UserActions : MviUserIntent {
    data class OnGetDataClicked(val dataId: Int) : FormA1UserActions()
}
