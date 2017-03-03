package com.pioneers.safwa.earnedvaluemanagementcalculator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_FINISH;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_ID;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_MANHOURS;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_NAME;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_PRICE;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_START;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.TABLE_CONTACTS;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.R.id.projectname;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.R.id.projectnamelayout;

/**
 * Created by safwa on 1/25/2017.
 */

public class EarnedValue extends AppCompatActivity {
   private EditText projname ;

    private static final String TAG = "EarnedValue";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earnedvalue2);
        Button calculate = (Button) findViewById(R.id.calc);
        projname = (EditText) findViewById(projectname);
        final TextInputLayout projnamelayout = (TextInputLayout) findViewById(projectnamelayout);
        final MyprojectHandler database=new MyprojectHandler(EarnedValue.this);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String projectname = projname.getText().toString();
if (projectname.matches("")){
    new AlertDialog.Builder(EarnedValue.this )
            .setMessage( "Missing Project Name" ).show();}
                else {
                    calcearned();
                }
            }
        });
    }
    private void calcearned() {
        try{
            EditText ev = (EditText) findViewById(R.id.earnedvalue);
            EditText pv = (EditText) findViewById(R.id.plan);
            EditText ac = (EditText) findViewById(R.id.actual);
            TextView earnedcalc = (TextView) findViewById(R.id.earnedshow);
            EditText projname = (EditText) findViewById(projectname);
            String projectname = projname.getText().toString();
            double plan, actual, earned, eaci, vaci, etci, cpii, spii, tcpii;
            plan = Double.parseDouble(pv.getText().toString());
            actual = Double.parseDouble(ac.getText().toString());
            earned = Double.parseDouble(ev.getText().toString());

            cpii = (float) (earned / actual);
            spii = (float) (earned / plan);

            SQLiteDatabase database = new MyprojectHandler(this).getReadableDatabase();
            final String SELECT_EMPLOYEE_WITH_EMPLOYER = "SELECT * " +
                    "FROM " + TABLE_CONTACTS + " WHERE " + KEY_NAME + " like ?";
            String[] selectionArgs = {"%" + projectname + "%"};

            Cursor cursor = database.rawQuery(SELECT_EMPLOYEE_WITH_EMPLOYER, selectionArgs);
                if (cursor != null )
                    cursor.moveToFirst();
               Project dBprojects = new Project(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),
                        cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_PRICE)),
                        cursor.getString(cursor.getColumnIndex(KEY_START)),
                        cursor.getString(cursor.getColumnIndex(KEY_MANHOURS)),
                        cursor.getString(cursor.getColumnIndex(KEY_FINISH)));
                double BAC = Double.valueOf(dBprojects.getmanhours()).doubleValue();
                double projvalue = Double.valueOf(dBprojects.getpvalue()).doubleValue();

                if(plan>BAC || earned>BAC) {
                   Toast toast= Toast.makeText(this, "Wrong Plan or Earned Values ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
          else {
                    eaci = (BAC / cpii);
                    vaci = (BAC - eaci);
                    etci = (eaci - actual);
                    tcpii = ((BAC - earned) / (BAC - actual));
                    earnedcalc.setVisibility(View.VISIBLE);
                    DecimalFormat df = new DecimalFormat("#,###,###,###");
                    DecimalFormat df2 = new DecimalFormat("0.000");
                    earnedcalc.setText(
                            "project Name:"+cursor.getString(cursor.getColumnIndex(KEY_NAME))+"\n"+
                            "CPI: " + df2.format(cpii) + "\n" +
                            "SPI: " + df2.format(spii) + "\n" +
                            "BAC (ManHours): " + df.format(BAC) + "\n" +
                            "Project Value:" + df.format(projvalue) + "\n" +
                            "EAC: " + df.format(eaci) + "\n" +
                            "VAC: " + df.format(vaci) + "\n" +
                            "ETC: " + df.format(etci) + "\n" +
                            "TCPI: " + df2.format(tcpii));
            //        database.close();
                }

        }
            catch(Exception e){
                Log.e(TAG, "Error", e);
               Toast toast= Toast.makeText(this, "Wrong or Missing Data", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                return;
            }

        }

        }






