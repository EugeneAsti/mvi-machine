package ru.aeyu.mvi_machine.mvi_machine.fragment

fun interface OnGetError {
    fun handleError(throwable: Throwable)
}