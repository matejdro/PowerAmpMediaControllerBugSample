package com.matejdro.poweramppausetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.notificationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
        });

        findViewById(R.id.pauseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePoweramp();
            }
        });
    }

    private void pausePoweramp() {
        MediaSessionManager mediaSessionManager = (MediaSessionManager) getSystemService(MEDIA_SESSION_SERVICE);

        ComponentName notificationServiceName = new ComponentName(this, NotificationService.class);

        MediaController powerAmpController = null;
        for (MediaController controller : mediaSessionManager.getActiveSessions(notificationServiceName)) {
            if (controller.getPackageName().equals("com.maxmpz.audioplayer")) {
                powerAmpController = controller;
            }
        }

        powerAmpController.getTransportControls().pause();
    }
}
