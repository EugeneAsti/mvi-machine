package ru.aeyu.mvi_machine.mvi_machine.reducer

import ru.aeyu.mvi_machine.mvi_machine.MviInternalIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState

fun interface InternalActionsReducer<I : MviInternalIntent, S : ViewState> {
    /** Данная функция возвращает состояние на основе текущего в зависимости от каких-то
     * внутренних действий типа [MviInternalIntent]
     * @param curState текущее состояние
     * @param internalAction внутреннее действие
     * @param onError callback для отправки ошибок пользователю
     * @param onNews callback для отправки различных сообщений пользователю
     * @return [S] типа [ViewState] новое или текущее состояние
     */
    fun reduce(
        curState: S,
        internalAction: I,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): S
}