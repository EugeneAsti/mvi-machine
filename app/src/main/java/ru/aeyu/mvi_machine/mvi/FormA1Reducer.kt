package ru.aeyu.mvi_machine.mvi

import ru.aeyu.mvi_machine.mvi.actions.internal.FormA1InternalActions
import ru.aeyu.mvi_machine.mvi.actions.view.FormA1UserActions
import ru.aeyu.mvi_machine.mvi.states.FormA1ViewState
import ru.aeyu.mvi_machine.mvi_machine.reducer.InternalActionsReducer
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer
import ru.aeyu.mvi_machine.mvi_machine.reducer.UserActionsReducer

//class FormA1Reducer : ReducerOld<FormA1UserActions, FormA1InternalActions, FormA1ViewState> {
//    override fun reduce(curState: FormA1ViewState, internalAction: FormA1InternalActions,
//                        onError: ((Throwable) -> Unit)?,
//                        onNews: ((String) -> Unit)?): FormA1ViewState {
//        return when(internalAction){
//            is FormA1InternalActions.OnGetDataSuccess ->
//                curState.copy(isLoading = false, dataObject = internalAction.newData)
//            is FormA1InternalActions.OnFailure -> {
//                if (onError != null) {
//                    onError(internalAction.throwable)
//                }
//                curState.copy(isLoading = false)
//            }FormA1InternalActions.OnLoad ->
//                curState.copy(isLoading = true)
//            FormA1InternalActions.OnLoadEnd ->
//                curState.copy(isLoading = false)
//        }
//    }
//
//    override fun reduce(curState: FormA1ViewState, userAction: FormA1UserActions,
//                        onError: ((Throwable) -> Unit)?,
//                        onNews: ((String) -> Unit)?): FormA1ViewState {
//        return when(userAction){
//            is FormA1UserActions.OnGetDataClicked -> curState.copy(isLoading = false, dataObject = null)
//        }
//    }
//
//}

class FormA1Reducer : Reducer<FormA1UserActions, FormA1InternalActions, FormA1ViewState>() {
    override val userActionReducer: UserActionsReducer<FormA1UserActions, FormA1ViewState> =
        UserActionsReducer { curState, userAction, onError, onNews ->
            when (userAction) {
                is FormA1UserActions.OnGetDataClicked -> curState.copy(
                    isLoading = false,
                    dataObject = null
                )
            }
        }

    override val internalActionsReducer: InternalActionsReducer<FormA1InternalActions, FormA1ViewState> =
        InternalActionsReducer { curState, internalAction, onError, onNews ->
            when (internalAction) {
                is FormA1InternalActions.OnGetDataSuccess ->
                    curState.copy(isLoading = false, dataObject = internalAction.newData)

                is FormA1InternalActions.OnFailure -> {
                    if (onError != null) {
                        onError(internalAction.throwable)
                    }
                    curState.copy(isLoading = false)
                }

                FormA1InternalActions.OnLoad ->
                    curState.copy(isLoading = true)

                FormA1InternalActions.OnLoadEnd ->
                    curState.copy(isLoading = false)
            }
        }
}