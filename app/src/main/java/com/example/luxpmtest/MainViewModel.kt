package com.example.luxpmtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    companion object {
        const val DEFAULT_EMAIL = "test@luxpmsoft.com"
        const val DEFAULT_PASSWORD = "test1234!"
    }

    private val _isValidSignupForm = MutableLiveData<Boolean>()
    private val _isValidSignupPassword = MutableLiveData<Boolean>()
    private val _isValidSignupConfirmPassword = MutableLiveData<Boolean>()
    private val _isValidSignupEmail = MutableLiveData<Boolean>()
    private val _isValidSignupTerm = MutableLiveData<Boolean>()
    private val _isValidSignIn = MutableLiveData<Boolean>()

    val isValidSignupForm: LiveData<Boolean> = _isValidSignupForm
    val isValidSignupPassword: LiveData<Boolean> = _isValidSignupPassword
    val isValidSignupConfirmPassword: LiveData<Boolean> = _isValidSignupConfirmPassword
    val isValidSignupEmail: LiveData<Boolean> = _isValidSignupEmail
    val isValidSignupTerm: LiveData<Boolean> = _isValidSignupTerm
    val isValidSignIn: LiveData<Boolean> = _isValidSignIn

    init {
        _isValidSignupForm.value = false
    }

    /**
     * Use to set valid form data
     */
    fun setValidSignupForm(isValid: Boolean) {
        _isValidSignupForm.value = isValid
    }

    /**
     * Use to set password validations
     */
    fun setIsValidSignupPassword(isValid: Boolean) {
        _isValidSignupPassword.value = isValid
    }

    /**
     * Use to set Conform password validation
     */
    fun setIsValidSignupConfirmPassword(isValid: Boolean) {
        _isValidSignupConfirmPassword.value = isValid
    }

    /**
     * Use to set Email validations
     */
    fun setIsValidSignupEmail(isValid: Boolean) {
        _isValidSignupEmail.value = isValid
    }

    /**
     * Use to set Term and Condition validations
     */
    fun setIsValidSignupTerm(isValid: Boolean) {
        _isValidSignupTerm.value = isValid
    }

    /**
     * Use to Check Static Login details
     */
    fun checkLogin(email: String, password: String) {
        _isValidSignIn.value = (email == DEFAULT_EMAIL && password == DEFAULT_PASSWORD)
    }
}