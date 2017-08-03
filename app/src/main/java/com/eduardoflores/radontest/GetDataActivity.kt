package com.eduardoflores.radontest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GetDataActivity : AppCompatActivity() {
    val TAG = "GetDataActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data)

        // write to db
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("Hello again!. User is " + FirebaseAuth.getInstance().currentUser?.email)

        // read from db
        myRef.addValueEventListener(object : ValueEventListener{
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
}
