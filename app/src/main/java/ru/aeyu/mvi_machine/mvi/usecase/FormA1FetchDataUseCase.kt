package ru.aeyu.mvi_machine.mvi.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import ru.aeyu.mvi_machine.mvi.actions.FormAActions
import ru.aeyu.mvi_machine.mvi_machine.SomeUseCase
import ru.aeyu.mvi_machine.mvi.repository.FormA1GetDataRepository
import ru.aeyu.mvi_machine.mvi.states.FormAViewState

class FormA1FetchDataUseCase(
    private val formA1GetDataRepository: FormA1GetDataRepository
) : SomeUseCase<FormAActions, FormAViewState> {

    override fun fetchData(
        someAction: FormAActions,
        currentState: FormAViewState?
    ): Flow<FormAActions> = flow {
        when (someAction) {
            is FormAActions.OnGetDataClicked -> {
                emit(FormAActions.OnLoad)
                emit(fetchFormData(someAction.dataId))
            }
            else -> emit(someAction)
        }
    }

    private suspend fun fetchFormData(dataId: Int): FormAActions =
        formA1GetDataRepository.getFormA1Data(dataId)
            .map { data ->
                delay(1500)
                data.fold(onSuccess = {
                    FormAActions.OnGetDataSuccess(it)
                },{
                    FormAActions.OnFailure(it)
                })
            }.catch { throwable -> FormAActions.OnFailure(throwable) }
            .single()


}

