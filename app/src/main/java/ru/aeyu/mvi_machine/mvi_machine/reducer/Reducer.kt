package ru.aeyu.mvi_machine.mvi_machine.reducer

import ru.aeyu.mvi_machine.mvi_machine.MviInternalIntent
import ru.aeyu.mvi_machine.mvi_machine.MviUserIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState

/**
 * Класс "редуктор" - преобразующий действия в состояния
 * Здесь [MviUserIntent] тип пользовательских действий
 *  [MviInternalIntent] тип внутренних реакций, как результат работы обращения к данным
 *  [ViewState] тип возможных состояний
 */
abstract class Reducer<A : MviUserIntent, I : MviInternalIntent, S : ViewState> {
    /**
     * функциональный интерфейс для обработки "пользовательских" действий
     */
    abstract val userActionReducer: UserActionsReducer<A, S>

    /**
     * Функциональный интерфейс для обработки внутренних действий
     */
    abstract val internalActionsReducer: InternalActionsReducer<I, S>
}