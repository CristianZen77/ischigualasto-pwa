package com.ischigualasto.app.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationService extends Worker {
    private static final String CHANNEL_ID = "ISCHIGUALASTO_CHANNEL";

    public NotificationService(Context context, WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        // Aquí iría la lógica para verificar nuevos eventos o noticias
        createNotificationChannel();
        showNotification("Nuevo evento en Ischigualasto", "¡Descubre las últimas novedades del parque!");
        return Result.success();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Ischigualasto Notificaciones",
                NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Canal de notificaciones para eventos y noticias de Ischigualasto");
            
            NotificationManager notificationManager = getApplicationContext()
                .getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) 
            getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}
