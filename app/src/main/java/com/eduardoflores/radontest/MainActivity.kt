package com.eduardoflores.radontest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * https://console.firebase.google.com/project/radontest-1005/overview
 */

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // analytics
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "test")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "some_name")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        // authentication
        val mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        Log.d(TAG, "Current user: " + currentUser)

        if (currentUser == null) {
            signUpNewUser(mAuth)
        }
    }

    fun signUpNewUser(auth: FirebaseAuth) {
        val email = "toomanyeduardos@gmail.com"
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
