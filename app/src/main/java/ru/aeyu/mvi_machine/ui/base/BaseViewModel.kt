package ru.aeyu.mvi_machine.ui.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import ru.aeyu.mvi_machine.mvi_machine.MviModel
import ru.aeyu.mvi_machine.mvi_machine.ViewIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState
import ru.aeyu.mvi_machine.mvi_machine.reducer.Reducer

abstract class BaseViewModel<
        UiAction : ViewIntent,
        InternalAction : ViewIntent,
        UiState : ViewState>(
    app: Application,
) : AndroidViewModel(app) {

    private val mviCoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            processCoroutineErrors(throwable)
        }

    protected val mainContext = Dispatchers.Main + mviCoroutineExceptionHandler
    protected val ioContext = Dispatchers.IO + mviCoroutineExceptionHandler

    protected abstract fun processCoroutineErrors(throwable: Throwable)

    protected abstract val initialState: UiState
    protected abstract val reducer: Reducer<UiAction, InternalAction, UiState>
    val myMviModel: MviModel<UiAction, InternalAction, UiState> by lazy {MviModel(initialState)}


    protected fun printLog(message: String) {
//        if (BuildConfig.DEBUG)
        Log.i("!!!###!!!", message)
    }

    abstract suspend fun handleAction(someAction: UiAction)

    override fun onCleared() {
        printLog("MainViewModel.onCleared()")
        myMviModel.dispose()
        super.onCleared()
    }
}