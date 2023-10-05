package ru.aeyu.mvi_machine.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import ru.aeyu.mvi_machine.mvi_machine.ViewIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState
import ru.aeyu.mvi_machine.mvi_machine.fragment.MviFragment
import ru.aeyu.mvi_machine.mvi_machine.fragment.OnGetError
import ru.aeyu.mvi_machine.mvi_machine.fragment.OnGetNews

abstract class MviBaseFragment<ViewBindingClass : ViewBinding,
        UiAction : ViewIntent,
        InternalAction: ViewIntent,
        UiState : ViewState,
        ViewModelClass : MviBaseViewModel<UiAction, InternalAction, UiState>>
    : BaseFragment<ViewBindingClass, ViewModelClass>(), MviFragment<UiAction, UiState> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printLog("onViewCreated")
        startCollectUiStates()
        startCollectUiErrors()
        startCollectUiNews()
    }

    private fun startCollectUiStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myMviModel.uiState.collect { state ->
                    onGetState.handleState(state)
                }
            }
        }
    }

    private fun startCollectUiErrors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myMviModel.uiError.collect { throwable ->
                    onGetError.handleError(throwable)
                }
            }
        }
    }

    private fun startCollectUiNews() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myMviModel.uiNews.collect { newsMessage ->
                    onGetNews.handleNews(newsMessage)
                }
            }
        }
    }

    protected fun hideKeyBoard() {
        val inputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    fun sendAction(newAction: UiAction) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.handleAction(newAction)
        }
    }
    override val onGetError: OnGetError = OnGetError {
        showErrorDialog("Ошибка! ${it.localizedMessage}")
    }
    override val onGetNews: OnGetNews = OnGetNews {
        showSnackBar(it)
    }

}