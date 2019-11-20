package com.example.app;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.app.activities.TelaHomeActivity;

public class BootService extends IntentService {

    private PowerManager.WakeLock wakeLock;

    public BootService() {
        super("name");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "AppName:BooService");
        wakeLock.acquire();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //Lance a notificação aqui.
        int notificationId = 001;

        Intent viewIntent = new Intent(this, TelaHomeActivity.class);
        viewIntent.putExtra("1", "2");
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("")
                        .setContentText("Que tal da uma olhada nas suas tarefas agora?")
                        //.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setContentIntent(viewPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wakeLock.isHeld()) {
            //Verificou-se que o iluminar do ecrã
            //não acontecia devido ao WakeLock ser
            //rapidamente libertado(apesar de PowerManager.ON_AFTER_RELEASE !?).
            try {
                //Atrasa a libertação do WakeLock
                //de forma a permitir a iluminação do ecrâ.
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                wakeLock.release();
            }
        }
    }
}