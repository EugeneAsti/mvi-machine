package ru.aeyu.mvi_machine.mvi_machine

import kotlinx.coroutines.flow.Flow

/**
 * Интерфейс для UseCase-ов работающих с данными.
 * Здесь [ViewIntent] тип пользовательских действий
 * [ViewState] тип возможных состояний
 */
fun interface Middleware<A: ViewIntent, I: ViewIntent, S: ViewState> {
    /** Функция получения данных из некоего источника по какому-то действию пользователя
     * с учетом текущего состояния(если требуется)
     *  @param currentState текущее состояние на момент получения данных.
     *  @return Flow<A> поток flow типа [ViewIntent] возможных состояний при получении данных (напр. загрузка, ошибка и т.д.)
     * Например:
     * ```
     * fun fetchData(
     *         userAction: SomeUserAction,
     *         currentState: SomeState?
     *     ): Flow<SomeInternalAction> = flow {
     *         when (userAction) {
     *             is SomeUserAction.OnGetDataClicked -> {
     *                 emit(SomeInternalAction.OnLoad)
     *                 emit(getSomeData(userAction.dataId))
     *             }
     *         }
     *     }
     * ```
     */
    fun fetchData(userAction: A, currentState: S?) : Flow<I>
}