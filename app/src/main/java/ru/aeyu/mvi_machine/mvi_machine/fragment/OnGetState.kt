package ru.aeyu.mvi_machine.mvi_machine.fragment

import ru.aeyu.mvi_machine.mvi_machine.ViewState

fun interface OnGetState<S: ViewState> {
    fun handleState(newState: S)
}