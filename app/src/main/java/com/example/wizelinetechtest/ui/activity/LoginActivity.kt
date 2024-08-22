package com.example.wizelinetechtest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wizelinetechtest.R
import com.example.wizelinetechtest.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity that handles the login process and uses view binding for its UI components and
 * dependency injection with Dagger Hilt.
 * @property binding The view binding that represents the activity's layout: "activity_login.xml"
 */
@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    /**
     * Function called when the activity is first created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding object and indicate the layout that must be displayed as content.
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set a click listener to the button of the login screen.
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Check if the credentials inserted by the user are correct.
            if(username == "user" && password == "password"){

                // Send the user to the next screen: the MainActivity page;
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {

                // Show a message error indicating about the wrong credentials.
                Toast.makeText(this,
                    getString(R.string.incorrect_credentials_msg), Toast.LENGTH_LONG).show()
            }
        }
    }

}