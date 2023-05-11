package me.stefanallen.chatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//Activity for user sign-up
class SignUp : AppCompatActivity() {

    //For UI elements
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        // Initialize ui elements
        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if(name.isNullOrBlank() || email.isNullOrBlank() || password.isNullOrBlank()){
                // Error message if any of the required fields are missing
                Toast.makeText(this@SignUp, "Missing Name, Username or Password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            signUp(name, email, password)
        }
    }

    // Handling user sign-up
    private fun signUp(name: String, email: String, password: String) {
        //logic for creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for jumping to home and Sign-up successful
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // Error message if sign-up fails
                    Toast.makeText(this@SignUp, "Some error occurred invalid input.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Adding user to the database
    private fun addUserToDatabase(name: String, email: String, uid: String) {
        // Reference Firebase Realtime Database
        mDbRef = FirebaseDatabase.getInstance().getReference()
        // Adding users to node in the database with the UID
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}