package com.example.app.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmSender {

    public static void agendarNotificacao(Context context, Date dataNotificacao) {
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        if (alarmIntent != null) {
            alarmMgr.cancel(alarmIntent);
        }

        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date = (format.parse("04/12/2019 18:32:10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        alarmMgr.set(AlarmManager.RTC_WAKEUP, date.getTime(), alarmIntent);
    }
}
