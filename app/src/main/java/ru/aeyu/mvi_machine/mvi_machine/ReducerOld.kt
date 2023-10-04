package ru.aeyu.mvi_machine.mvi_machine


/**
 * Интерфейс "редуктор" классы реализующие данный интерфейс, должны возвращать
 * новое или текущее состояние после выполнения некоего пользовательского действия.
 * Здесь [MviUserIntent] тип пользовательских действий
 *  [MviInternalIntent] тип внутренних реакций, как результат работы обращения к данным
 *  [ViewState] тип возможных состояний
 */
interface ReducerOld<A : MviUserIntent, I : MviInternalIntent, S : ViewState> {
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
