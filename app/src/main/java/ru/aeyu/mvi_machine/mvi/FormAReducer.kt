package ru.aeyu.mvi_machine.mvi

import ru.aeyu.mvi_machine.mvi.actions.FormAIntent
import ru.aeyu.mvi_machine.mvi.actions.FormAInternalIntent
import ru.aeyu.mvi_machine.mvi.states.FormAViewState
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer

class FormAReducer : Reducer<FormAIntent, FormAInternalIntent, FormAViewState>() {

    override fun handleInternalIntent(
        curState: FormAViewState,
        internalAction: FormAInternalIntent,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): FormAViewState {
        return when(internalAction) {
            is FormAInternalIntent.OnGetDataSuccess ->
                curState.copy(isLoading = false, dataObject = internalAction.newData)

            is FormAInternalIntent.OnFailure -> {
                if (onError != null) {
                    onError(internalAction.throwable)
                }
                curState.copy(isLoading = false)
            }

            FormAInternalIntent.OnLoad ->
                curState.copy(isLoading = true)

            FormAInternalIntent.OnLoadEnd ->
                curState.copy(isLoading = false)
            FormAInternalIntent.Idle -> curState
        }
    }

    override fun handleUserIntent(
        curState: FormAViewState,
        userAction: FormAIntent,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): FormAViewState {
        return when(userAction){
            is FormAIntent.OnGetDataClicked -> {
                curState.copy(
                    isLoading = false,
                    dataObject = null
                )
            }
            FormAIntent.OnNextFragment -> {
                curState
            }
        }
    }
}