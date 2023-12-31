package ru.aeyu.mvi_machine.mvi_machine

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer

/**
 * Ядро обработки пользовательских или внутренних действий и выдачи состояния.
 * Чтобы начать с ним работать, необходимо наследоваться от данного класса.
 * @param initialState начальное состояние
 * Cостояния выпускаются в [uiState]
 * Ошибки выпускаются в [uiError]
 * Информационные сообщения выпускаются в [uiNews]
 *
 * Чтобы получить новое состояние в котором могут быть также и данные, нужно вызвать функцию
 * [handleAction] она выпустит новое состояние в [uiState], если есть ошибка,
 * то ее можно поймать в [uiError], а различные сообщения для пользователя, не связанные с состоянием
 * могут быть получены при прослушивании [uiNews]
 *
 */
class MviModel<A : ViewIntent, I : ViewIntent, S : ViewState>(
    initialState: S
) {

    private val mviCoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            processCoroutineErrors(throwable)
        }

    private val mainContext = Dispatchers.Main + mviCoroutineExceptionHandler
    private val mviCoroutineScope =
        CoroutineScope(mainContext + SupervisorJob() + mviCoroutineExceptionHandler)


    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initialState)

    /**
     * Здесь можно отловить состояния испускаемые редуктором [Reducer].
     */
    val uiState: StateFlow<S> = _uiState.asStateFlow()
    private val currentState: S = uiState.value

    private val _uiError: MutableSharedFlow<Throwable> = MutableSharedFlow()

    /**
     * Здесь можно отловить возникающие в процессе получения данных ошибки.
     */
    val uiError: SharedFlow<Throwable> = _uiError.asSharedFlow()


    private val _uiNews: Channel<String> = Channel()

    /**
     * Здесь можно отловить информационные текстовые сообщения.
     */
    val uiNews: Flow<String> = _uiNews.receiveAsFlow()

    private fun processCoroutineErrors(throwable: Throwable) {
        mviCoroutineScope.launch {
            _uiError.emit(throwable)
        }
    }

    /**
     * Выполняем эту функцию, при получении пользовательских действий
     */
    fun handleAction(
        reducer: Reducer<A, I, S>,
        dataUseCase: Middleware<A, I, S>?,
        userAction: A,
    ) {
        mviCoroutineScope.launch {
            onUserAction(reducer, dataUseCase, userAction, currentState)
                .collect { newState ->
                    printLog("handleUserAction.State: $newState")
                    sendNewState(newState)
                }
        }
    }

    /**
     * Принудительное завершение обработки пользовательских действий
     */
    fun dispose() {
        try {
            mviCoroutineScope.cancel("Dispose method called")
        } catch (iLs: IllegalStateException) {
            iLs.printStackTrace()
            printLog("MviModel.dispose() err: ${iLs.localizedMessage}")
        }
    }

    private suspend fun sendNewState(newState: S) {
        _uiState.emit(newState)
    }

    private fun onUserAction(
        reducer: Reducer<A, I, S>,
        middleware: Middleware<A, I, S>?,
        someAction: A,
        currentState: S,
    ): Flow<S> =
        middleware?.fetchData(someAction, currentState)?.map { internalAction ->
            reducer.actionsReducer.reduce(currentState, internalAction, onError, onNews)
        } ?: flowOf(reducer.actionsReducer.reduce(currentState, someAction, onError, onNews))

    private val onError: ((Throwable) -> Unit) = { throwable ->
        mviCoroutineScope.launch(mainContext) {
            printLog("Ошибка: ${throwable.localizedMessage}")
            _uiError.emit(throwable)
        }
    }

    private val onNews: (String) -> Unit = { news ->
        printLog("Передано сообщение: $news")
        _uiNews.trySend(news)
    }

    protected fun printLog(message: String) {
//        if (BuildConfig.DEBUG)
        Log.i("!!!###!!!", message)
    }
}