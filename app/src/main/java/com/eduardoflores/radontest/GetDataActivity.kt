package com.eduardoflores.radontest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_get_data.*

class GetDataActivity : AppCompatActivity() {
    val TAG = "GetDataActivity"
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data)

        setupDB()
        writeToDB()
        readFromDB()

        button_setup_push_notification.setOnClickListener{sendPushNotification()}
    }

    private fun setupDB() {
        val database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("message")
    }

    private fun writeToDB() {
        databaseReference.setValue("Hello again!. User is " + FirebaseAuth.getInstance().currentUser?.email)
    }

    private fun readFromDB() {
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot?.getValue(String::class.java)
                Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError?) {
                // failed to read values
                Log.w(TAG, "Failed to read values.", error?.toException())
            }
        })
    }

    private fun sendPushNotification() {
        SetupFirebaseCloudMessaging()
    }
}
