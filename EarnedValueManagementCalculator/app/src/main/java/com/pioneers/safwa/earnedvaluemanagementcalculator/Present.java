package com.pioneers.safwa.earnedvaluemanagementcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.Tracker;

/**
 * Created by safwa on 2/7/2017.
 */

public class Present extends AppCompatActivity
{
    private Tracker mTracker;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.present);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        Button gotoproj=(Button) findViewById(R.id.gottoproj);
        Button gotoearn=(Button)findViewById(R.id.gotoearn);

        gotoproj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Present.this, com.pioneers.safwa.earnedvaluemanagementcalculator.MainActivity.class));
            }
        });
        gotoearn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                startActivity(new Intent(Present.this, com.pioneers.safwa.earnedvaluemanagementcalculator.Earnedvaluedata.class));
            }
        });

    }
}
