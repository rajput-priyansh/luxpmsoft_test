package com.example.luxpmtest.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.luxpmtest.MainNavigation
import com.example.luxpmtest.MainViewModel
import com.example.luxpmtest.R
import com.example.luxpmtest.databinding.FragmentSingInBinding

class FragmentSignIn: Fragment(R.layout.fragment_sing_in) {

    private lateinit var binder: FragmentSingInBinding

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var mCallback: MainNavigation

    override fun onAttach(context: Context) {
        try {
            mCallback = activity as MainNavigation
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString() + " must implement HeadlineListener"
            )
        }
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder = FragmentSingInBinding.bind(view)

        initUi()
    }

    private fun initUi() {
        binder.tvNoAccountSignUp.setOnClickListener {
            mCallback.onNavigation(R.id.actionSignInFragmentSignUp)
        }

        binder.btnSignIn.setOnClickListener {
            if (validateSignInForm()) {
                viewModel.checkLogin(binder.etEmail.text.toString(),
                    binder.etPassword.text.toString())
            } else {
                Toast.makeText(context, R.string.key_22, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateSignInForm() : Boolean {
        return !binder.etEmail.text.isNullOrEmpty() &&
            !binder.etPassword.text.isNullOrEmpty()
    }
}