package ru.aeyu.mvi_machine.mvi_machine.reducer

import ru.aeyu.mvi_machine.mvi_machine.MviActions
import ru.aeyu.mvi_machine.mvi_machine.ViewState

/**
 * Класс "редуктор" - преобразующий действия в состояния
 * Здесь [MviActions] тип пользовательских действий
 *  [ViewState] тип возможных состояний
 */
abstract class Reducer<A : MviActions, S : ViewState> {

    /**
     * Функциональный интерфейс для обработки внутренних действий
     */
    abstract val actionsReducer: ActionsReducer<A, S>
}