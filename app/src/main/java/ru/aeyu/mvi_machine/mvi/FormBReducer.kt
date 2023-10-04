package ru.aeyu.mvi_machine.mvi

import ru.aeyu.mvi_machine.mvi.actions.FormBIntent
import ru.aeyu.mvi_machine.mvi.actions.FormBInternalIntent
import ru.aeyu.mvi_machine.mvi.states.FormBViewState
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer

class FormBReducer : Reducer<FormBIntent, FormBInternalIntent, FormBViewState>() {

    override fun handleUserIntent(
        curState: FormBViewState,
        userAction: FormBIntent,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): FormBViewState {
        return when(userAction){
            FormBIntent.OnToastButtonClicked -> {
                if(onNews != null)
                    onNews("Toast shown!!!")
                curState.copy()
            }
        }
    }

    override fun handleInternalIntent(
        curState: FormBViewState,
        internalAction: FormBInternalIntent,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): FormBViewState {
        return when (internalAction) {
            is FormBInternalIntent.OnError -> {
                if (onError != null) {
                    onError(internalAction.throwable)
                }
                curState.copy(isLoading = false)
            }
            FormBInternalIntent.OnLoad -> curState.copy(isLoading = true)
            FormBInternalIntent.OnLoadEnd -> curState.copy(isLoading = false)
        }
    }
}