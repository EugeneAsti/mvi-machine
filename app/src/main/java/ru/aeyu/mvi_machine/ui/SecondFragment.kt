package ru.aeyu.mvi_machine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import ru.aeyu.mvi_machine.databinding.FragmentSecondBinding
import ru.aeyu.mvi_machine.mvi.actions.FormBActions
import ru.aeyu.mvi_machine.mvi.states.FormBViewState
import ru.aeyu.mvi_machine.ui.base.BaseFragment

class SecondFragment : BaseFragment<FragmentSecondBinding,
        FormBActions,
        FormBViewState,
        SecondViewModel>() {
    override fun handleState(uiState: FormBViewState) {
        binding.progressCircular.isVisible = uiState.isLoading
        binding.btnTestToast.isClickable = !uiState.isLoading
    }

    override val viewModel: SecondViewModel by activityViewModels()

    override fun getBindingInstance(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Boolean
    ): FragmentSecondBinding = FragmentSecondBinding.inflate(inflater, container, false)

    override fun handleError(throwable: Throwable) {
        showErrorDialog("SecondFragment ERR! ${throwable.message}")
    }

    override fun handleNews(newsMessage: String) {
        showSnackBar(newsMessage)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTestToast.setOnClickListener {
            setAction(FormBActions.OnToastButtonClicked)
        }
    }
}