package ru.aeyu.mvi_machine.mvi_machine

interface MviFragment<A: MviActions, S : ViewState>{
    fun setAction(newAction: A)
    fun handleState(uiState: S)
    fun handleError(throwable: Throwable)
    fun handleNews(newsMessage: String)
}