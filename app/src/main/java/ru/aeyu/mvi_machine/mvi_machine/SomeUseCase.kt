package ru.aeyu.mvi_machine.mvi_machine

import kotlinx.coroutines.flow.Flow

/**
 * Интерфейс для UseCase-ов работающих с данными.
 * Здесь [MviUserIntent] тип пользовательских действий
 * [MviInternalIntent] тип внутренних реакций, как результат работы обращения к данным
 * [ViewState] тип возможных состояний
 */
interface SomeUseCase<A: MviUserIntent, I: MviInternalIntent, S: ViewState> {
    /** Функция получения данных из некоего источника по какому-то действию пользователя
     * с учетом текущего состояния(если требуется)
     * @param userAction пользовательское действие, для получения данных из UseCase
     *  @param currentState текущее состояние на момент получения данных.
     *  @return Flow<I> поток flow типа [MviInternalIntent] возможных состояний при получении данных (напр. загрузка, ошибка и т.д.)
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