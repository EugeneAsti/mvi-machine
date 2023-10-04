package ru.aeyu.mvi_machine.ui

import android.app.Application
import ru.aeyu.mvi_machine.mvi.FormA1MviModel
import ru.aeyu.mvi_machine.mvi.FormA1Reducer
import ru.aeyu.mvi_machine.mvi.actions.internal.FormA1InternalActions
import ru.aeyu.mvi_machine.mvi.actions.view.FormA1UserActions
import ru.aeyu.mvi_machine.mvi.repository.FormAGetDataRepository
import ru.aeyu.mvi_machine.mvi.states.FormA1ViewState
import ru.aeyu.mvi_machine.mvi.usecase.FormA1FetchDataUseCase
import ru.aeyu.mvi_machine.ui.base.BaseViewModel

class MainViewModel(
    app: Application,
) : BaseViewModel<FormA1UserActions, FormA1InternalActions, FormA1ViewState>(app) {

    override fun processCoroutineErrors(throwable: Throwable) {
        printLog("[MainViewModel] coroutine Err: ${throwable.localizedMessage}")
    }

    override val myMviModel: FormA1MviModel = FormA1MviModel(FormA1Reducer())

    private val formA1UseCase: FormA1FetchDataUseCase = FormA1FetchDataUseCase(
        FormAGetDataRepository()
    )

    override suspend fun handleUserActions(userAction: FormA1UserActions) {
        when (userAction) {
            is FormA1UserActions.OnGetDataClicked -> {
                myMviModel.handleUserAction(
                    dataUseCase = formA1UseCase,
                    userAction = userAction,
                )
            }
        }
    }
}