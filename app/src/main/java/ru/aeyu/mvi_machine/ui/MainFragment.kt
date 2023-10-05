package ru.aeyu.mvi_machine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import ru.aeyu.mvi_machine.R
import ru.aeyu.mvi_machine.databinding.FragmentMainBinding
import ru.aeyu.mvi_machine.mvi.actions.FormAIntent
import ru.aeyu.mvi_machine.mvi.actions.FormAInternalIntent
import ru.aeyu.mvi_machine.mvi.states.FormAViewState
import ru.aeyu.mvi_machine.mvi_machine.fragment.OnGetState
import ru.aeyu.mvi_machine.ui.base.MviBaseFragment
import kotlin.random.Random


class MainFragment
    : MviBaseFragment<FragmentMainBinding,
        FormAIntent,
        FormAInternalIntent,
        FormAViewState,
        MainViewModelMvi>() {

    private lateinit var textView: TextView
    private lateinit var btn: Button
    override val viewModel: MainViewModelMvi by activityViewModels()

    override fun getBindingInstance(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Boolean
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn = binding.btnStopCore
        textView = binding.someText
        btn.setOnClickListener {
            sendAction(FormAIntent.OnGetDataClicked(Random(System.nanoTime()).nextInt(100)))
        }
        binding.btnOpenNextFragment.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                addToBackStack("NameF")
                hide(this@MainFragment)
                add(R.id.fragment_container, SecondFragment(), "SecondFragment")
            }
        }
    }

    override val onGetState: OnGetState<FormAViewState> = OnGetState { uiState ->
        binding.progressCircular.isVisible = uiState.isLoading
        btn.isClickable = !uiState.isLoading
        binding.someText.text = if (uiState.dataObject == null)
            ""
        else
            uiState.dataObject.comment
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}