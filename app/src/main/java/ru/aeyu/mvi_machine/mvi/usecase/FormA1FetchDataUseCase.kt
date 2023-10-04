package ru.aeyu.mvi_machine.mvi.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import ru.aeyu.mvi_machine.mvi.actions.view.FormA1UserActions
import ru.aeyu.mvi_machine.mvi_machine.SomeUseCase
import ru.aeyu.mvi_machine.mvi.actions.internal.FormA1InternalActions
import ru.aeyu.mvi_machine.mvi.repository.FormAGetDataRepository
import ru.aeyu.mvi_machine.mvi.states.FormA1ViewState

class FormA1FetchDataUseCase(
    private val formAGetDataRepository: FormAGetDataRepository
) : SomeUseCase<FormA1UserActions, FormA1InternalActions, FormA1ViewState> {

    override fun fetchData(
        userAction: FormA1UserActions,
        currentState: FormA1ViewState?
    ): Flow<FormA1InternalActions> = flow {
        when (userAction) {
            is FormA1UserActions.OnGetDataClicked -> {
                emit(FormA1InternalActions.OnLoad)
                emit(fetchFormData(userAction.dataId))
            }
        }
    }

    private suspend fun fetchFormData(dataId: Int): FormA1InternalActions =
        formAGetDataRepository.getFormA1Data(dataId)
            .map { data ->
                delay(1500)
                data.fold(onSuccess = {
                    FormA1InternalActions.OnGetDataSuccess(it)
                },{
                    FormA1InternalActions.OnFailure(it)
                })
            }.catch { throwable -> FormA1InternalActions.OnFailure(throwable) }
            .single()


}

