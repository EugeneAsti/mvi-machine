package ru.aeyu.mvi_machine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import ru.aeyu.mvi_machine.databinding.FragmentSecondBinding
import ru.aeyu.mvi_machine.mvi.actions.FormBIntent
import ru.aeyu.mvi_machine.mvi.actions.FormBInternalIntent
import ru.aeyu.mvi_machine.mvi.states.FormBViewState
import ru.aeyu.mvi_machine.mvi_machine.fragment.OnGetState
import ru.aeyu.mvi_machine.ui.base.MviBaseFragment

class SecondFragment : MviBaseFragment<FragmentSecondBinding,
        FormBIntent,
        FormBInternalIntent,
        FormBViewState,
        SecondViewModelMvi>() {

    override val viewModel: SecondViewModelMvi by activityViewModels()

    override fun getBindingInstance(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Boolean
    ): FragmentSecondBinding = FragmentSecondBinding.inflate(inflater, container, false)

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTestToast.setOnClickListener {
            sendAction(FormBIntent.OnToastButtonClicked)
        }
    }
    override val onGetState: OnGetState<FormBViewState> = OnGetState {uiState ->
        binding.progressCircular.isVisible = uiState.isLoading
        binding.btnTestToast.isClickable = !uiState.isLoading
    }

}