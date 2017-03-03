package com.pioneers.safwa.earnedvaluemanagementcalculator;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by safwa on 2/16/2017.
 */

public class AddProject extends AppCompatActivity {
    private EditText lastnameEditText;
    private EditText firstnameEditText;
    private EditText originaldurationEditText;
    private EditText employedEditText;
    private EditText jobDescEditText;
    private EditText dobEditText;
    private static final String TAG = "AddProject";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MyprojectHandler db=new MyprojectHandler(AddProject.this);
        setContentView(R.layout.activity_employee);
        lastnameEditText = (EditText) findViewById(R.id.lastnameEditText);
        firstnameEditText = (EditText) findViewById(R.id.firstnameEditText);
        originaldurationEditText = (EditText) findViewById(R.id.originaldurationEditText);
        employedEditText = (EditText) findViewById(R.id.employedEditText);
        jobDescEditText = (EditText) findViewById(R.id.jobDescEditText);
        dobEditText = (EditText) findViewById(R.id.dobEditText);
        lastnameEditText.addTextChangedListener(onTextChangedListener());
        // final TextInputLayout projnamelayout = (TextInputLayout) findViewById(firstnameInputLayout);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String projectonamo = firstnameEditText.getText().toString();
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                                dobEditText.getText().toString()));
                        double date1 = calendar.getTimeInMillis();

                        calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                                employedEditText.getText().toString()));
                        double date2 = calendar.getTimeInMillis();
                       /* if (date2 < date1){
                            Toast.makeText( "Finish date less than Start Date", Toast.LENGTH_LONG).show();
                        }
                        else{
                            values.put(COLUMN_DATE_OF_FINISH, date2);}*/
                    if ( projectonamo.toString().matches("")) {
                        new AlertDialog.Builder(AddProject.this )
                                .setMessage( "Missing Project Name" ).show();
                    } else {
                    /*  saveToDB();*/
                        db.addproject(new Project(firstnameEditText.getText().toString(),
                                lastnameEditText.getText().toString().replaceAll(",", ""),
                                dobEditText.getText().toString(),jobDescEditText.getText().toString(),
                                employedEditText.getText().toString()));
                        Toast.makeText(getBaseContext(), "New Project  " + firstnameEditText.getText().toString()+" successfuly added ", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e) {
                    Log.e(TAG, "Error", e);
                    Toast.makeText(getBaseContext(), "Wrong Or Missing Data ", Toast.LENGTH_LONG).show();

                    return;
                }
            }
        });
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {

                lastnameEditText.removeTextChangedListener(this);
                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);
                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);
                    lastnameEditText.setText(formattedString);
                    lastnameEditText.setSelection(lastnameEditText.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                lastnameEditText.addTextChangedListener(this);
            }
        };
    }
}
  /*  private void saveToDB() {
        SQLiteDatabase database = new DBQHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        long ordur=Long.valueOf(originaldurationEditText.getText().toString());
        try {
            values.put(COLUMN_PROJECTNAME, firstnameEditText.getText().toString());
            values.put(COLUMN_VALUE, lastnameEditText.getText().toString().replaceAll(",", ""));
            values.put(COLUMN_MANHOURS, jobDescEditText.getText().toString());
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
            Toast toast = Toast.makeText(this, "Wrong or Missing Data", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                    dobEditText.getText().toString()));
            long date1 = calendar.getTimeInMillis();
            values.put(COLUMN_DATE_OF_START, date1);

            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                    employedEditText.getText().toString()));
            long date2 = calendar.getTimeInMillis();
            if (date2 < date1){
                Toast.makeText(this, "Finish date less than Start Date", Toast.LENGTH_LONG).show();
            }
            else{
                values.put(COLUMN_DATE_OF_FINISH, date2);}
            Duration duration = new Duration(date1, date2);
            int days = (int) duration.getStandardDays();
            int months=Math.round(days/31);
            int  dura= months + 1;
           /*     if ((dura)!=ordur){
                    Toast.makeText(this, "Wrong Duration..Duration Should be "+dura, Toast.LENGTH_LONG).show();
                }
                else{
                    values.put(ORIGINAL_DURATION, String.valueOf(dura));
                }*/

            //    database.delete(TABLE_NAME, null, values);
            //    Toast.makeText(this, "New Project  " + firstnameEditText.getText().toString()+" successfuly added ", Toast.LENGTH_LONG).show();
 /*           Toast.makeText(this, "New Project  " + newRowId+" successfuly added ", Toast.LENGTH_LONG).show();
            database.close();
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
            Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
            return;
        }*/





