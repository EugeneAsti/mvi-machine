package ru.aeyu.mvi_machine.ui

import android.app.AlertDialog
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
import ru.aeyu.mvi_machine.mvi.actions.FormAActions
import ru.aeyu.mvi_machine.mvi.states.FormAViewState
import ru.aeyu.mvi_machine.ui.base.BaseFragment
import kotlin.random.Random


class MainFragment
    : BaseFragment<FragmentMainBinding,
        FormAActions,
        FormAViewState,
        MainViewModel>() {

    private lateinit var textView: TextView
    private lateinit var btn: Button
    override val viewModel: MainViewModel by activityViewModels()

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
            setAction(FormAActions.OnGetDataClicked(Random(2).nextInt(100)))
        }
        binding.btnOpenNextFragment.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                addToBackStack("NameF")
                hide(this@MainFragment)
                add(R.id.fragment_container, SecondFragment(), "SecondFragment")
            }
        }
    }


    override fun handleError(throwable: Throwable) {
        showAlertDialog("Ошибка! ${throwable.localizedMessage}")
    }

    override fun handleNews(newsMessage: String) {
        showSnackBar(newsMessage)
    }

    override fun handleState(uiState: FormAViewState) {
        binding.progressCircular.isVisible = uiState.isLoading
        btn.isClickable = !uiState.isLoading
        binding.someText.text = if (uiState.dataObject == null)
            ""
        else
            uiState.dataObject.comment
    }

    private fun showAlertDialog(message: String) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Ошибка")
            setMessage(message)
            setNegativeButton("Понятно", null)
            setCancelable(false)
        }.show()
    }

}