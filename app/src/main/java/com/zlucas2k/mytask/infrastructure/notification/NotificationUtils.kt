package com.zlucas2k.mytask.infrastructure.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.common.utils.Constants

object NotificationUtils {

    fun showNotification(ctx: Context, title: String, description: String) {
        val notificationManager = ctx.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)

        val builder = createNotificationCompat(ctx, title, description)

        notificationManager.notify(1, builder.build())
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.NOTIFICATION.CHANNEL_ID,
                Constants.NOTIFICATION.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotificationCompat(ctx: Context, title: String, description: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(ctx, Constants.NOTIFICATION.CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    }
}