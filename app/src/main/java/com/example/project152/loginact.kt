package com.example.project152

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class loginact : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var signIn: Button
    private lateinit var txtRegister: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginact)

        txtRegister = findViewById(R.id.txtRegister)
        txtRegister.setOnClickListener(this)

        signIn = findViewById(R.id.actlogin)
        signIn.setOnClickListener(this)

        edtEmail = findViewById(R.id.logEmail)
        edtPassword = findViewById(R.id.logPassword)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.txtRegister -> startActivity(Intent(this, register::class.java))
            R.id.actlogin -> loginUser()
        }
    }

    private fun loginUser() {
        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        if (email.isEmpty()) {
            edtEmail.error = "Email is required"
            edtEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.error = "Please enter a valid email"
            edtEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            edtPassword.error = "Password is required"
            edtPassword.requestFocus()
            return
        }

        if (password.length < 5) {
            edtPassword.error = "Min password length is 5 characters!"
            edtPassword.requestFocus()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this@loginact, tablayout_custom::class.java))
                    Toast.makeText(this@loginact, "Login Successfully", Toast.LENGTH_LONG).show()
                } else {
                    try {
                        throw task.exception ?: Exception("Login failed")
                    } catch (e: FirebaseAuthInvalidUserException) {
                        edtEmail.error = "No account found with this email."
                        edtEmail.requestFocus()
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        edtPassword.error = "Invalid password."
                        edtPassword.requestFocus()
                    } catch (e: Exception) {
                        Log.e("LOGIN", e.message.toString())
                        Toast.makeText(this@loginact, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}
