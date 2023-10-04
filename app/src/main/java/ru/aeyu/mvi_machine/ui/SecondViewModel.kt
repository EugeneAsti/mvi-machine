package ru.aeyu.mvi_machine.ui

import android.app.Application
import ru.aeyu.mvi_machine.mvi.FormBMviModel
import ru.aeyu.mvi_machine.mvi.FormBReducer
import ru.aeyu.mvi_machine.mvi.actions.FormBActions
import ru.aeyu.mvi_machine.mvi.states.FormBViewState
import ru.aeyu.mvi_machine.ui.base.BaseViewModel

class SecondViewModel(
    app: Application,
) : BaseViewModel<FormBActions, FormBViewState>(app) {

    override fun processCoroutineErrors(throwable: Throwable) {
        printLog("[MainViewModel] coroutine Err: ${throwable.localizedMessage}")
    }

    override val myMviModel: FormBMviModel = FormBMviModel(FormBReducer())

    override suspend fun handleAction(someAction: FormBActions) {
        when (someAction) {
            FormBActions.OnToastButtonClicked -> {
                myMviModel.handleAction(null, someAction)
            }
            else -> {}
        }
    }
}