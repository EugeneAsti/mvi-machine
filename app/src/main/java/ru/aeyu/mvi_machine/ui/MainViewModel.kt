package ru.aeyu.mvi_machine.ui

import android.app.Application
import ru.aeyu.mvi_machine.mvi.FormAMviModel
import ru.aeyu.mvi_machine.mvi.FormAReducer
import ru.aeyu.mvi_machine.mvi.actions.FormAActions
import ru.aeyu.mvi_machine.mvi.repository.FormA1GetDataRepository
import ru.aeyu.mvi_machine.mvi.states.FormAViewState
import ru.aeyu.mvi_machine.mvi.usecase.FormA1FetchDataUseCase
import ru.aeyu.mvi_machine.ui.base.BaseViewModel

class MainViewModel(
    app: Application,
) : BaseViewModel<FormAActions, FormAViewState>(app) {

    override fun processCoroutineErrors(throwable: Throwable) {
        printLog("[MainViewModel] coroutine Err: ${throwable.localizedMessage}")
    }

    override val myMviModel: FormAMviModel = FormAMviModel(FormAReducer())

    private val formA1UseCase: FormA1FetchDataUseCase = FormA1FetchDataUseCase(
        FormA1GetDataRepository()
    )

    override suspend fun handleAction(someAction: FormAActions) {
        when (someAction) {
            is FormAActions.OnGetDataClicked -> {
                myMviModel.handleAction(
                    dataUseCase = formA1UseCase,
                    userAction = someAction,
                )
            }
            else -> {
                //Игнорируем ненужные действия
            }
        }
    }
}