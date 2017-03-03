package com.pioneers.safwa.earnedvaluemanagementcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView( R.layout.activity_main);

       Button createproject=(Button) findViewById(R.id.secondActivityButton);
       Button update=(Button)findViewById(R.id.firstActivityButton);
      Button earnedcalc =(Button)findViewById(R.id.thirdActivityButton);

       createproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.pioneers.safwa.earnedvaluemanagementcalculator.AddProject.class));
            }
        });
        earnedcalc.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.pioneers.safwa.earnedvaluemanagementcalculator.EarnedValue.class));
        }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.pioneers.safwa.earnedvaluemanagementcalculator.UpdateProjectDetails.class));
            }
        });

    }
}
