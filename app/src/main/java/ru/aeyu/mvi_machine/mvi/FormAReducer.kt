package ru.aeyu.mvi_machine.mvi

import ru.aeyu.mvi_machine.mvi.actions.FormAActions
import ru.aeyu.mvi_machine.mvi.states.FormAViewState
import ru.aeyu.mvi_machine.mvi_machine.reducer.ActionsReducer
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer

class FormAReducer : Reducer<FormAActions, FormAViewState>() {
    override val actionsReducer: ActionsReducer<FormAActions, FormAViewState> =
        ActionsReducer { curState, internalAction, onError, onNews ->
            when (internalAction) {
                is FormAActions.OnGetDataSuccess ->
                    curState.copy(isLoading = false, dataObject = internalAction.newData)

                is FormAActions.OnFailure -> {
                    if (onError != null) {
                        onError(internalAction.throwable)
                    }
                    curState.copy(isLoading = false)
                }

                FormAActions.OnLoad ->
                    curState.copy(isLoading = true)

                FormAActions.OnLoadEnd ->
                    curState.copy(isLoading = false)

                is FormAActions.OnGetDataClicked -> {
                    curState.copy(
                        isLoading = false,
                        dataObject = null
                    )
                }
                FormAActions.OnNextFragment -> {
                    curState.copy()
                }
            }
        }
}