package com.example.luxpmtest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.luxpmtest.MainNavigation
import com.example.luxpmtest.MainViewModel
import com.example.luxpmtest.R
import com.example.luxpmtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainNavigation {

    private lateinit var binding: ActivityMainBinding

    private lateinit var opNavHostFragment: NavHostFragment
    private lateinit var opController: NavController

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    private val opNavigationlistener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
        }


    override fun onResume() {
        super.onResume()
        opController.addOnDestinationChangedListener(opNavigationlistener)
    }

    override fun onPause() {
        super.onPause()
        opController.removeOnDestinationChangedListener(opNavigationlistener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        opNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment

        opController = opNavHostFragment.navController

        setContentView(binding.root)

        observer()

    }

    private fun observer() {
        viewModel.isValidSignIn.observe(this, {
            if (it) {
                startActivity(Intent(this@MainActivity, WelComeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            } else {
                Toast.makeText(this@MainActivity, R.string.key_23, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onNavigation(pageId: Int) {
        findNavController(R.id.fragmentNavHost).navigate(pageId)
    }
}