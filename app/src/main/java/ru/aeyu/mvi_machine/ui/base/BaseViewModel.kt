package ru.aeyu.mvi_machine.ui.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import ru.aeyu.mvi_machine.mvi_machine.MviModel
import ru.aeyu.mvi_machine.mvi_machine.MviActions
import ru.aeyu.mvi_machine.mvi_machine.ViewState

abstract class BaseViewModel<
        UiAction : MviActions,
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

    abstract val myMviModel: MviModel<UiAction, UiState>

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