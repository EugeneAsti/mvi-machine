package ru.aeyu.mvi_machine.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<ViewBindingClass : ViewBinding,
        ViewModelClass : ViewModel>
    : Fragment() {

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

    override fun onDestroyView() {
        printLog("[BaseFragment] onDestroyView")
        _binding = null
        super.onDestroyView()
    }

    protected fun printLog(message: String) {
//        if (BuildConfig.DEBUG)
        Log.i("!!!###!!!", message)
    }

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
}