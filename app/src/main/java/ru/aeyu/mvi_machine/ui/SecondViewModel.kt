package ru.aeyu.mvi_machine.ui

import android.app.Application
import ru.aeyu.mvi_machine.mvi.FormBReducer
import ru.aeyu.mvi_machine.mvi.actions.FormBIntent
import ru.aeyu.mvi_machine.mvi.actions.FormBInternalIntent
import ru.aeyu.mvi_machine.mvi.states.FormBViewState
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer
import ru.aeyu.mvi_machine.ui.base.BaseViewModel

class SecondViewModel(
    app: Application,
) : BaseViewModel<FormBIntent, FormBInternalIntent, FormBViewState>(app) {

    override fun processCoroutineErrors(throwable: Throwable) {
        printLog("[MainViewModel] coroutine Err: ${throwable.localizedMessage}")
    }

    override val initialState: FormBViewState = FormBViewState(false)

    override val reducer: Reducer<FormBIntent, FormBInternalIntent, FormBViewState> = FormBReducer()



    override suspend fun handleAction(someAction: FormBIntent) {
        when (someAction) {
            FormBIntent.OnToastButtonClicked -> {
                myMviModel.handleAction(reducer, null, someAction)
            }
        }
    }
}