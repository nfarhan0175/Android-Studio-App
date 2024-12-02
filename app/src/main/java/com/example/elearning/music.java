package com.example.elearning;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;


public class music extends Service {
    MediaPlayer mp;
    Handler handler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startID){
        mp = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mp.setLooping(true);
        mp.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopMusic();
            }
        }, 1000);
        return START_NOT_STICKY;
    }
    private void stopMusic() {
        if (mp != null) {
            mp.stop();
            mp.release(); // Release resources
            mp = null; // Set to null to avoid memory leaks
        }
    }
    public void onDestroy(){
        stopMusic();
        super.onDestroy();
    }
}