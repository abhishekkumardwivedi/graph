package com.example.abhishek.graph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    GraphView graphView;
    double x, y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = 0;

        graphView = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();

        for (int i = 0; i< 500; i++) {
            x = x + 0.1;
            y = Math.sin(x);
            series.appendData(new DataPoint(x, y), true, 1000);
        }
        graphView.addSeries(series);
    }
}