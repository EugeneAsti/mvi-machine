package ru.aeyu.mvi_machine.mvi.actions

import ru.aeyu.mvi_machine.mvi_machine.InternalIntent
import ru.aeyu.mvi_machine.mvi_machine.UserIntent

sealed class FormBInternalIntent : InternalIntent {
    object OnLoad: FormBInternalIntent()
    object OnLoadEnd: FormBInternalIntent()
    data class OnError(val throwable: Throwable) : FormBInternalIntent()
}

sealed class FormBIntent : UserIntent{
    object OnToastButtonClicked : FormBIntent()
}