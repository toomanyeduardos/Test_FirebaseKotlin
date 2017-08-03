package com.eduardoflores.radontest.pushNotification

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by eflores on 8/2/17.
 */
class SetupFirebaseCloudMessaging : FirebaseInstanceIdService() {
    val TAG = "SetupFirebaseMessaging"

    override fun onTokenRefresh() {
        // Get updated instanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: " + refreshedToken)
    }
}