package com.example.app.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.example.app.classes.Medicamento;
import com.example.app.classes.Tarefa;

import java.util.Date;

public class AlarmSender {

    public static void agendarTarefa(Context context, Tarefa tarefa){
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        int code = (int) new Date().getTime();

        intent.putExtra("titulo", tarefa.getTarefa());
        intent.putExtra("texto", tarefa.getDescricao());
        intent.putExtra("canal", tarefa.getCodTarefa()+"t");
        intent.putExtra("id", code);

        alarmIntent = PendingIntent.getBroadcast(context, code, intent, 0);

        if (alarmIntent != null) {
            alarmMgr.cancel(alarmIntent);
        }

        alarmMgr.set(AlarmManager.RTC_WAKEUP, tarefa.getDataRealizacao().getTime(), alarmIntent);
    }

    public static void agendarMedicamento(Context context, Medicamento medicamento){
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        int code = (int) new Date().getTime();

        intent.putExtra("titulo", "Está na hora de tomar "+ medicamento.getNomeMedicamento());
        intent.putExtra("texto", "Consumir " + medicamento.getQuantidade() + " " + medicamento.getMedida());
        intent.putExtra("canal", medicamento.getCodMedicamento()+"m");
        intent.putExtra("id", code);

        alarmIntent = PendingIntent.getBroadcast(context, code, intent, 0);

        if (alarmIntent != null) {
            alarmMgr.cancel(alarmIntent);
        }

        switch (medicamento.getFrequencia()){
            case QUATRO:
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, medicamento.getHorario().getTime(), AlarmManager.INTERVAL_HOUR * 4, alarmIntent);
            case SEIS:
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, medicamento.getHorario().getTime(), AlarmManager.INTERVAL_HOUR * 6, alarmIntent);
            case OITO:
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, medicamento.getHorario().getTime(), AlarmManager.INTERVAL_HOUR * 8, alarmIntent);
            case DOZE:
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, medicamento.getHorario().getTime(), AlarmManager.INTERVAL_HALF_DAY, alarmIntent);
            case DIARIA:
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, medicamento.getHorario().getTime(), AlarmManager.INTERVAL_DAY, alarmIntent);
            case SEMANAL:
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, medicamento.getHorario().getTime(), AlarmManager.INTERVAL_DAY * 7, alarmIntent);
            case MENSAL:
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, medicamento.getHorario().getTime(), AlarmManager.INTERVAL_DAY * 30, alarmIntent);
        }
    }
}
