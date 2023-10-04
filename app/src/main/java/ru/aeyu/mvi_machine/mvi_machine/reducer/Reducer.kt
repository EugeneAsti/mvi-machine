package ru.aeyu.mvi_machine.mvi_machine.reducer

import ru.aeyu.mvi_machine.mvi_machine.InternalIntent
import ru.aeyu.mvi_machine.mvi_machine.UserIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState
import java.lang.ClassCastException

/**
 * Класс "редуктор" - преобразующий действия в состояния
 * Здесь [ViewIntent] тип пользовательских действий
 *  [ViewState] тип возможных состояний
 */
abstract class Reducer<A : ViewIntent, I : ViewIntent, S : ViewState> {

    /**
     * Функциональный интерфейс для обработки внутренних действий
     */
    @Suppress("UNCHECKED_CAST")
    val actionsReducer: ActionsReducer<S> =
        ActionsReducer { curState, someAction, onError, onNews ->
            when (someAction) {
                is UserIntent -> {
                    handleUserIntent(curState, someAction as A, onError, onNews)
                }

                is InternalIntent -> {
                    handleInternalIntent(curState, someAction as I, onError, onNews)
                }
                else -> { throw ClassCastException("ActionsReducer. Unknown action: $someAction")}
            }
        }

    abstract fun handleInternalIntent(
        curState: S,
        internalAction: I,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): S

    abstract fun handleUserIntent(
        curState: S,
        userAction: A,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): S


}