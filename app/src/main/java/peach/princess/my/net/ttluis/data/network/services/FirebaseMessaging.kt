package peach.princess.my.net.ttluis.data.network.services

import com.google.firebase.messaging.FirebaseMessagingService
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import peach.princess.my.net.ttluis.R


class FirebaseMessaging: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        val data = remoteMessage!!.data
        val mBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.escudoescom)
            .setAutoCancel(true)
            .setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(5, mBuilder.build())
    }

}