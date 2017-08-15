package com.example.abhishek.graph;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Random;

public class RemoteMonitorService extends Service {
    private static final String TAG = RemoteMonitorService.class.getSimpleName();
    private final Handler mHandler = new Handler();
    double mLastRandom = 2;
    Random mRand = new Random();

    public RemoteMonitorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        testLoop();
        return super.onStartCommand(intent, flags, startId);
    }

    private double getRandom() {
        return mLastRandom += mRand.nextDouble() * 0.5 - 0.25;
    }


    private void testLoop() {
        Runnable mTimer2 = new Runnable() {
            @Override
            public void run() {
                sendMessage();
                mHandler.postDelayed(this, 200);
            }
        };
        mHandler.postDelayed(mTimer2, 1000);
    }

    private void sendMessage() {
//        Log.d("sender", "Broadcasting message");

        Intent intent = new Intent("custom-event-name");
        intent.putExtra("message", getRandom());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
