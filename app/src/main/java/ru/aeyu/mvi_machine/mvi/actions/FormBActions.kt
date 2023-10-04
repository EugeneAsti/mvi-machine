package ru.aeyu.mvi_machine.mvi.actions

import ru.aeyu.mvi_machine.mvi_machine.MviActions

sealed class FormBActions : MviActions {
    object OnToastButtonClicked : FormBActions()
    object OnLoad: FormBActions()
    object OnLoadEnd: FormBActions()
    data class OnError(val throwable: Throwable) : FormBActions()
}