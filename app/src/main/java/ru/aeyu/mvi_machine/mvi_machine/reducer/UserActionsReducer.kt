package ru.aeyu.mvi_machine.mvi_machine.reducer

import ru.aeyu.mvi_machine.mvi_machine.MviUserIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState


fun interface UserActionsReducer<A : MviUserIntent, S : ViewState> {
    /** Данная функция возвращает состояние на основе текущего в зависимости от действий пользователя
     * @param curState текущее состояние
     * @param userAction пользовательское действие
     * @param onError callback для отправки ошибок пользователю
     * @param onNews callback для отправки различных сообщений пользователю
     * @return [S] типа [ViewState] новое или текущее состояние
     */
    fun reduce(
        curState: S,
        userAction: A,
        onError: ((Throwable) -> Unit)?,
        onNews: ((String) -> Unit)?
    ): S
}