package ru.aeyu.mvi_machine.ui.base

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import ru.aeyu.mvi_machine.mvi_machine.MviFragment
import ru.aeyu.mvi_machine.mvi_machine.MviInternalIntent
import ru.aeyu.mvi_machine.mvi_machine.MviUserIntent
import ru.aeyu.mvi_machine.mvi_machine.ViewState

abstract class BaseFragment<ViewBindingClass : ViewBinding,
        UiAction : MviUserIntent,
        UiInternalAction : MviInternalIntent,
        UiState : ViewState,
        ViewModelClass : BaseViewModel<UiAction, UiInternalAction, UiState>>
    : Fragment(), MviFragment<UiAction, UiState> {

    abstract val viewModel: ViewModelClass

    private var _binding: ViewBindingClass? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getBindingInstance(inflater, container, false)
        return binding.root
    }

    abstract fun getBindingInstance(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Boolean
    ): ViewBindingClass?

    protected fun showErrorDialog(text: String) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Ошибка")
            setMessage(text)
            setPositiveButton("Понятно", null)
        }.show()
    }

    protected fun showSnackBar(messageText: String) {
        Snackbar.make(binding.root, messageText, Snackbar.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printLog("onViewCreated")
        startCollectUiStates()
        startCollectUiErrors()
    }

    private fun startCollectUiStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myMviModel.uiState.collect { state ->
                    handleState(state)
                }
            }
        }
    }

    private fun startCollectUiErrors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myMviModel.uiError.collect { throwable ->
                    handleError(throwable)
                }
            }
        }
    }

    override fun onDestroyView() {
        printLog("[BaseFragment] onDestroyView")
        _binding = null
        super.onDestroyView()
    }

    protected fun printLog(message: String) {
//        if (BuildConfig.DEBUG)
        Log.i("!!!###!!!", message)
    }

    protected fun hideKeyBoard() {
        val inputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun setAction(newAction: UiAction) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.handleUserActions(newAction)
        }
    }

}