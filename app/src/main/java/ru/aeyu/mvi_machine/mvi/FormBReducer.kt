package ru.aeyu.mvi_machine.mvi

import ru.aeyu.mvi_machine.mvi.actions.FormBActions
import ru.aeyu.mvi_machine.mvi.states.FormBViewState
import ru.aeyu.mvi_machine.mvi_machine.reducer.ActionsReducer
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer

class FormBReducer : Reducer<FormBActions, FormBViewState>() {
    override val actionsReducer: ActionsReducer<FormBActions, FormBViewState> =
        ActionsReducer { curState, internalAction, onError, onNews ->
            when (internalAction) {
                is FormBActions.OnError -> {
                    if (onError != null) {
                        onError(internalAction.throwable)
                    }
                    curState.copy(isLoading = false)
                }
                FormBActions.OnLoad -> curState.copy(isLoading = true)
                FormBActions.OnLoadEnd -> curState.copy(isLoading = false)
                FormBActions.OnToastButtonClicked -> {
                    if(onNews != null)
                        onNews("Toast shown!!!")
                    curState.copy()
                }
            }
        }
}