package ru.aeyu.mvi_machine.mvi_machine.fragment

fun interface OnGetNews {
    fun handleNews(newsMessage: String)
}