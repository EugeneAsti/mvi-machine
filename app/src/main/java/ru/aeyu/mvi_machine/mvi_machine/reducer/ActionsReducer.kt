package ru.aeyu.mvi_machine.mvi_machine.reducer

import ru.aeyu.mvi_machine.mvi_machine.ViewIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState

fun interface ActionsReducer<S : ViewState> {
    /** Данная функция возвращает состояние на основе текущего в зависимости от каких-то
     * внутренних действий типа [ViewIntent]
     * @param curState текущее состояние
     * @param someAction внутреннее действие
     * @param onError callback для отправки ошибок пользователю
     * @param onNews callback для отправки различных сообщений пользователю
     * @return [S] типа [ViewState] новое или текущее состояние
     */
    fun reduce(
        curState: S,
        someAction: ViewIntent,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): S
}