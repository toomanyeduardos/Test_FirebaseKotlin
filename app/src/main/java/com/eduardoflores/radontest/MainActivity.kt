package com.eduardoflores.radontest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://console.firebase.google.com/project/radontest-1005/overview
 */

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var mFirebaseAnalytics : FirebaseAnalytics
    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAnalytics()
        if (getCurrentFirebaseUser() == null) {
            signUpNewUser(mAuth)
        }

        // lambda learning resource
        // https://antonioleiva.com/lambdas-kotlin-android/
        button_get_data.setOnClickListener{startGetDataActivity()}
    }

    private fun startGetDataActivity() {
        val intent = Intent(this, GetDataActivity::class.java)
        intent.putExtra("KEY", "Some extra")
        startActivity(intent)
    }

    private fun getCurrentFirebaseUser(): FirebaseUser? {
        // authentication
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        Log.d(TAG, "Current user: " + currentUser)
        return currentUser
    }

    private fun setupAnalytics() {
        // analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "test")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "some_name")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    fun signUpNewUser(auth: FirebaseAuth) {
        val email = System.currentTimeMillis().toString() + "@example.com"
        val password = "#T6H7bnhkh567NBcccn00"
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{task: Task<AuthResult> ->
            if (task.isSuccessful) {
                Log.d(TAG, "Task successful!")
            } else {
                Log.d(TAG, "Task not successful!")
            }
        }
    }
}
