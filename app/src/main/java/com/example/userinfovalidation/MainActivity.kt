package com.example.userinfovalidation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val save: Button = findViewById(R.id.saveButton)

        save.setOnClickListener() {
            checkInput()
        }

        val clear: Button = findViewById(R.id.clearButton)
        clear.setOnLongClickListener {
            clearInputFields()
            true
        }

    }

    private fun checkInput() {
        val errorTxt: TextView = findViewById(R.id.errorMessage)
        errorTxt.text = getErrorMessages()
    }

    private fun getErrorMessages(): String {
        val firstName: EditText = findViewById(R.id.firstName)
        val emailText: EditText = findViewById(R.id.email)
        val lastNameText: EditText = findViewById(R.id.lastName)
        val usernameText: EditText = findViewById(R.id.username)
        val ageText: EditText = findViewById(R.id.age)

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
        val firstName: EditText = findViewById(R.id.firstName)
        val emailText: EditText = findViewById(R.id.email)
        val lastNameText: EditText = findViewById(R.id.lastName)
        val usernameText: EditText = findViewById(R.id.username)
        val ageText: EditText = findViewById(R.id.age)
        val errorTxt: TextView = findViewById(R.id.errorMessage)

        emailText.text.clear()
        firstName.text.clear()
        lastNameText.text.clear()
        usernameText.text.clear()
        ageText.text.clear()
        errorTxt.text = ""
    }
}
