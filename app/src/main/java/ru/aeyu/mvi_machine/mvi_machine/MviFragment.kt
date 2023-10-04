package ru.aeyu.mvi_machine.mvi_machine

interface MviFragment<A: MviUserIntent, S : ViewState>{
    fun setAction(newAction: A)
    fun handleState(uiState: S)
    fun handleError(throwable: Throwable)
}