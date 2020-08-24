package com.example.envsaqapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PaamindelseNoti extends BroadcastReceiver {

    public Integer NotiID = 0;


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ny notifikation")
                .setSmallIcon(R.drawable.ic_info)
                .setContentTitle("en venlig påmindelse")
                .setContentText("kom nu lige")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notifiktionsManager = NotificationManagerCompat.from(context);

        notifiktionsManager.notify(NotiID, builder.build());
        NotiID++;

    }
}
