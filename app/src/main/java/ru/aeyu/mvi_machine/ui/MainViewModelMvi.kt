package ru.aeyu.mvi_machine.ui

import android.app.Application
import ru.aeyu.mvi_machine.mvi.FormAReducer
import ru.aeyu.mvi_machine.mvi.actions.FormAIntent
import ru.aeyu.mvi_machine.mvi.actions.FormAInternalIntent
import ru.aeyu.mvi_machine.mvi.data.repository.FormA1GetDataRepository
import ru.aeyu.mvi_machine.mvi.data.usecase.GetDataUseCase
import ru.aeyu.mvi_machine.mvi.states.FormAViewState
import ru.aeyu.mvi_machine.mvi.middleware.FormA1Middleware
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer
import ru.aeyu.mvi_machine.ui.base.MviBaseViewModel

class MainViewModelMvi(
    app: Application,
) : MviBaseViewModel<FormAIntent, FormAInternalIntent, FormAViewState>(app) {

    override fun processCoroutineErrors(throwable: Throwable) {
        printLog("[MainViewModelMvi] coroutine Err: ${throwable.localizedMessage}")
    }

    override val initialState: FormAViewState = FormAViewState(isLoading = false, null)
    override val reducer: Reducer<FormAIntent, FormAInternalIntent, FormAViewState> = FormAReducer()

//    override val myMviModel: FormAMviModel = FormAMviModel(FormAReducer())


    private val formA1UseCase: FormA1Middleware = FormA1Middleware(
        GetDataUseCase(FormA1GetDataRepository())
    )

    override suspend fun handleAction(someAction: FormAIntent) {
        when (someAction) {
            is FormAIntent.OnGetDataClicked -> {
                myMviModel.handleAction(
                    reducer,
                    dataUseCase = formA1UseCase,
                    userAction = someAction,
                )
            }

            FormAIntent.OnNextFragment -> {
                myMviModel.dispose()
            }
        }
    }
}