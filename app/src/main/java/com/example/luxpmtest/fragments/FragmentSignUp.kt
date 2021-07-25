package com.example.luxpmtest.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.luxpmtest.MainNavigation
import com.example.luxpmtest.MainViewModel
import com.example.luxpmtest.R
import com.example.luxpmtest.databinding.FragmentSingUpBinding
import java.util.regex.Pattern

class FragmentSignUp : Fragment(R.layout.fragment_sing_up) {


    private lateinit var binder: FragmentSingUpBinding

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

        binder = FragmentSingUpBinding.bind(view)

        initUi()

        observer()
    }

    private fun observer() {
        viewModel.isValidSignupConfirmPassword.observe(viewLifecycleOwner, {
            binder.tilConfirmPassword.error = if (it) {
                null
            } else {
                getString(R.string.key_18)
            }
        })

        viewModel.isValidSignupEmail.observe(viewLifecycleOwner, {
            binder.tilEmail.error = if (it) {
                null
            } else {
                getString(R.string.key_17)
            }
        })

        viewModel.isValidSignupForm.observe(viewLifecycleOwner, {
            binder.btnSignUp.isEnabled = it ?: false
        })
    }

    private fun initUi() {
        val textWatcher = object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                isValidPassword(s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
                //Empty Method
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                //Empty Method
            }
        }

        binder.etPassword.addTextChangedListener(textWatcher)

        binder.etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Empty Method
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Empty Method
            }

            override fun afterTextChanged(s: Editable?) {
                isValidConfirmPassword(binder.etPassword.text.toString(), s.toString())
            }

        })

        binder.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Empty Method
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Empty Method
            }

            override fun afterTextChanged(s: Editable?) {
                isValidEmail(s.toString())
            }

        })

        binder.chkTerm.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setIsValidSignupTerm(isChecked)
            isValidForm()
        }

        binder.tvAlreadyAccount.setOnClickListener {
            mCallback.onNavigation(R.id.actionSignUpToFragmentSignIn)
        }

        binder.btnSignUp.setOnClickListener {
            mCallback.onNavigation(R.id.actionSignUpToFragmentSignIn)
        }
    }

    private fun isValidEmail(email: String) {
        viewModel.setIsValidSignupEmail(
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email.isNotEmpty()
            } else false
        )
        isValidForm()
    }

    private fun isValidConfirmPassword(password: String, confirmPassword: String) {
        viewModel.setIsValidSignupConfirmPassword(password == confirmPassword)
        isValidForm()
    }

    private fun isValidPassword(passwords: String) {
        val specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val upperCasePatten = Pattern.compile("[A-Z ]")
        val digitCasePatten = Pattern.compile("[0-9 ]")
        var flag = true

        binder.tvValidationOne.setCompoundDrawablesWithIntrinsicBounds(
            if (passwords.length < 8) {
                flag = false
                R.drawable.ic_unselected
            } else {
                flag = true
                R.drawable.ic_selected
            }, 0, 0, 0
        )

        binder.tvValidationFour.setCompoundDrawablesWithIntrinsicBounds(
            if (!specailCharPatten.matcher(passwords).find()) {
                flag = false
                R.drawable.ic_unselected
            } else {
                flag = true
                R.drawable.ic_selected
            }, 0, 0, 0
        )


        binder.tvValidationTwo.setCompoundDrawablesWithIntrinsicBounds(
            if (!upperCasePatten.matcher(passwords).find()) {
                flag = false
                R.drawable.ic_unselected
            } else {
                flag = true
                R.drawable.ic_selected
            }, 0, 0, 0
        )

        binder.tvValidationThree.setCompoundDrawablesWithIntrinsicBounds(
            if (!digitCasePatten.matcher(passwords).find()) {
                flag = false
                R.drawable.ic_unselected
            } else {
                flag = true
                R.drawable.ic_selected
            }, 0, 0, 0
        )

        viewModel.setIsValidSignupPassword(flag)

        isValidForm()
    }

    private fun isValidForm() {
        viewModel.setValidSignupForm(
            viewModel.isValidSignupPassword.value ?: false
                    && viewModel.isValidSignupEmail.value ?: false
                    && viewModel.isValidSignupConfirmPassword.value ?: false
                    && viewModel.isValidSignupTerm.value ?: false
        )
    }
}