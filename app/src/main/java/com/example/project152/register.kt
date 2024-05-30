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
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase

class register : AppCompatActivity(), View.OnClickListener {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var registerbtn: Button
    private lateinit var txtLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_act)
        mAuth = FirebaseAuth.getInstance()

        txtLogin = findViewById(R.id.txtLogin)
        txtLogin.setOnClickListener(this)

        registerbtn = findViewById(R.id.registerbtn)
        registerbtn.setOnClickListener(this)

        edtUsername = findViewById(R.id.regUser)
        edtEmail = findViewById(R.id.regEmail)
        edtPassword = findViewById(R.id.regPassword)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txtLogin -> startActivity(Intent(this, loginact::class.java))
            R.id.registerbtn -> registerUser()
        }
    }

    private fun registerUser() {
        val email = edtEmail.text.toString().trim()
        val username = edtUsername.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        if (username.isEmpty()) {
            edtUsername.error = "Username is required"
            edtUsername.requestFocus()
            return
        }

        if (email.isEmpty()) {
            edtEmail.error = "Email is required"
            edtEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.error = "Please provide valid email"
            edtEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            edtPassword.error = "Password is required"
            edtPassword.requestFocus()
            return
        }

        if (password.length < 5) {
            edtPassword.error = "Min password length is 5 characters"
            edtPassword.requestFocus()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = User(username, email, password)
                    Log.d("REGISTER", "createUserWithEmail:success")

                    FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user)
                        .addOnCompleteListener { innerTask ->
                            if (innerTask.isSuccessful) {
                                Log.d("REGISTER", "setValue:success")
                                Toast.makeText(
                                    this,
                                    "User has been registered successfully!",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Log.w("REGISTER", "setValue:failure", innerTask.exception)
                                Toast.makeText(
                                    this,
                                    "Failed to register. Try Again!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Log.w("REGISTER", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this,
                            "The email address is already in use by another account.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Log.w("REGISTER", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this, "Failed to register.", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}

