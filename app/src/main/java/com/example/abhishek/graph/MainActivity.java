package com.example.abhishek.graph;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    LineGraphSeries<DataPoint> series;
    GraphView graphView;

    private double graph2LastXValue = 5d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, RemoteMonitorService.class));

        series = new LineGraphSeries<>();

        graphView = (GraphView) findViewById(R.id.graph);
        graphView.addSeries(series);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(20);
    }

    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter("custom-event-name"));
        super.onResume();
//        mTimer2 = new Runnable() {
//            @Override
//            public void run() {
//                graph2LastXValue += 1d;
//                series.appendData(new DataPoint(graph2LastXValue, getRandom()), true, 40);
//                mHandler.postDelayed(this, 200);
//            }
//        };
//        mHandler.postDelayed(mTimer2, 1000);
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                mMessageReceiver);
//        mHandler.removeCallbacks(mTimer2);
        super.onPause();
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            // Get extra data included in the Intent
            double message = intent.getDoubleExtra("message", 0);
            Log.d("receiver", "Got message: " + message);
        }
    };
}
