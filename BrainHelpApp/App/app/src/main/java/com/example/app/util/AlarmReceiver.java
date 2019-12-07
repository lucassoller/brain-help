package com.example.app.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import com.example.app.activity.TelaHomeActivity;
import com.example.app.activity.TelaMedicamentosActivity;
import com.example.app.activity.TelaTarefasActivity;
import com.example.app.R;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;
    private String titulo;
    private String texto;
    private String canal;
    private int tela;
    private int id;

    @Override
    public void onReceive(Context context, Intent intent) {
       this.context = context;
       titulo = intent.getStringExtra("titulo");
       texto = intent.getStringExtra("texto");
       canal = intent.getStringExtra("canal");
       tela = intent.getIntExtra("tela", 0);
       id = intent.getIntExtra("id", 0);
       notificar();
    }

    private void notificar () {
        createNotificationChannel();
        Intent intent;
        if(tela == 0){
            intent = new Intent(context, TelaHomeActivity.class);
        }else if( tela == 1){
            intent = new Intent(context, TelaMedicamentosActivity.class);
        }else{
            intent = new Intent(context, TelaTarefasActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, canal)
                .setSmallIcon(R.drawable.brain)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setLights(Color.WHITE, 2000, 3000)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(id, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name ="nome";
            String description = "descricao";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(canal, name, importance);
            channel.setDescription(description);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
