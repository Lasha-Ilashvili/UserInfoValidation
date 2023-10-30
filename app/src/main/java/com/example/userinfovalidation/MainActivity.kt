package com.example.userinfovalidation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var firstName: EditText
    private lateinit var lastNameText: EditText
    private lateinit var emailText: EditText
    private lateinit var usernameText: EditText
    private lateinit var ageText: EditText
    private lateinit var errorTxt:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstName = findViewById(R.id.firstName)
        emailText = findViewById(R.id.email)
        lastNameText = findViewById(R.id.lastName)
        usernameText = findViewById(R.id.username)
        ageText = findViewById(R.id.age)

        val save: Button = findViewById(R.id.saveButton)
        save.setOnClickListener {
            checkInput()
        }

        val clear: Button = findViewById(R.id.clearButton)
        clear.setOnLongClickListener {
            clearInputFields()
            true
        }

    }

    private fun checkInput() {
        errorTxt = findViewById(R.id.errorMessage)
        errorTxt.text = getErrorMessages()
    }

    private fun getErrorMessages(): String {

        return when {
            emailText.text.toString().isEmpty() || lastNameText.text.toString().isEmpty() ||
                    usernameText.text.toString().isEmpty() || ageText.text.toString().isEmpty() ||
                    firstName.text.toString().isEmpty()
            -> "Error! Fill in every field."

            usernameText.text.toString().length < 10
            -> "Error! Username must be at least 10 characters long."

            !Patterns.EMAIL_ADDRESS.matcher(emailText.text.toString())
                .matches() -> "Error! Invalid email address."

            !isPositiveInteger(ageText.text.toString())
            -> "Error! Invalid age."

            else -> ""
        }
    }

    private fun isPositiveInteger(text: String): Boolean {
        val age = text.toIntOrNull()
        return age != null && age >= 0
    }

    private fun clearInputFields() {
        emailText.text.clear()
        firstName.text.clear()
        lastNameText.text.clear()
        usernameText.text.clear()
        ageText.text.clear()
        errorTxt.text = ""
    }
}
