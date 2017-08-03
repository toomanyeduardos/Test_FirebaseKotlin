package com.eduardoflores.radontest.pushNotification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseCloudMessagingService : FirebaseMessagingService() {
    val TAG = "MyFireBMessagingService"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: " + remoteMessage?.from)

        // message payload
        if (remoteMessage?.data?.size!! > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        // message notification (if any)
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message notification body: " + remoteMessage.notification)
        }
    }

}
