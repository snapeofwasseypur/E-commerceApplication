package com.example.e_commerce_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var e_mail: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var signUp: Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login2)

        mAuth = FirebaseAuth.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        e_mail = findViewById(R.id.edt_email)
        password = findViewById(R.id.edt_Password)
        login = findViewById(R.id.btn_login)
        signUp = findViewById(R.id.btn_signup)

        login.setOnClickListener() {
            val Email = e_mail.text.toString()
            val Password = password.text.toString()
            processLogin(Email, Password)
        }
        signUp.setOnClickListener() {
            val Email = e_mail.text.toString()
            val Password = password.text.toString()
            processSignUp(Email, Password)
        }
    }

    private fun processLogin(Email: String, Password: String) {
        mAuth.signInWithEmailAndPassword(Email, Password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // open activity to list the product by seller
                    val intent = Intent(this, UploadProduct::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun processSignUp(Email: String, Password: String) {
        mAuth.createUserWithEmailAndPassword(Email, Password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // open activity to list the product by seller
                    Toast.makeText(this, "SignUp Successful", Toast.LENGTH_SHORT).show()


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "SignUp Unsuccessful", Toast.LENGTH_SHORT).show()

                }
            }
    }
}