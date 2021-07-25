package com.example.luxpmtest.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.luxpmtest.MainNavigation
import com.example.luxpmtest.R
import com.example.luxpmtest.databinding.FragmentSplashBinding

class FragmentSplash: Fragment(R.layout.fragment_splash) {

    private lateinit var binder: FragmentSplashBinding

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

        binder = FragmentSplashBinding.bind(view)

        initUi()
    }

    private fun initUi() {
        binder.btnSignUp.setOnClickListener {
            mCallback.onNavigation(R.id.actionFragmentSignUp)
        }

        binder.tvSignIn.setOnClickListener {
            mCallback.onNavigation(R.id.actionFragmentSignIn)
        }
    }
}