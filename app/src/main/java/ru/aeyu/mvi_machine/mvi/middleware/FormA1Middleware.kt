package ru.aeyu.mvi_machine.mvi.middleware

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import ru.aeyu.mvi_machine.mvi.actions.FormAIntent
import ru.aeyu.mvi_machine.mvi.actions.FormAInternalIntent
import ru.aeyu.mvi_machine.mvi_machine.Middleware
import ru.aeyu.mvi_machine.mvi.states.FormAViewState
import ru.aeyu.mvi_machine.mvi.data.usecase.GetDataUseCase

class FormA1Middleware(
    private val getDataUseCase: GetDataUseCase
) : Middleware<FormAIntent, FormAInternalIntent, FormAViewState> {

    override fun fetchData(
        userAction: FormAIntent,
        currentState: FormAViewState?
    ): Flow<FormAInternalIntent> = flow {
        when (userAction) {
            is FormAIntent.OnGetDataClicked -> {
                emit(FormAInternalIntent.OnLoad)
                emit(fetchFormData(userAction.dataId))
            }
            else -> emit(FormAInternalIntent.Idle)
        }
    }

    private suspend fun fetchFormData(dataId: Int): FormAInternalIntent =
        getDataUseCase.getData(dataId)
            .map { data ->
                delay(1500)
                data.fold(onSuccess = {
                    FormAInternalIntent.OnGetDataSuccess(it)
                },{
                    FormAInternalIntent.OnFailure(it)
                })
            }.catch { throwable -> FormAInternalIntent.OnFailure(throwable) }
            .single()


}

