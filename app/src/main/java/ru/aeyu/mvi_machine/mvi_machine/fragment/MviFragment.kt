package ru.aeyu.mvi_machine.mvi_machine.fragment

import ru.aeyu.mvi_machine.mvi_machine.ViewIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState

interface MviFragment<A: ViewIntent, S : ViewState>{
    val onGetError: OnGetError
    val onGetNews: OnGetNews
    val onGetState: OnGetState<S>
}