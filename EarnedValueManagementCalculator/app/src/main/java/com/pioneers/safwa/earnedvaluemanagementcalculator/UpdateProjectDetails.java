package com.pioneers.safwa.earnedvaluemanagementcalculator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_FINISH;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_ID;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_MANHOURS;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_NAME;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_PRICE;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.KEY_START;
import static com.pioneers.safwa.earnedvaluemanagementcalculator.MyprojectHandler.TABLE_CONTACTS;


public class UpdateProjectDetails extends AppCompatActivity {

    private static final String TAG = "UpdateProjectDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projectdetailsupdate);
      // final SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();
        //final TextInputLayout projnamelayout = (TextInputLayout) findViewById(projnamee);
        Button  delbutton=(Button) findViewById(R.id.Delproj);
        delbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                EditText firstnameEditText = (EditText) findViewById(R.id.projectnameupd);
                String projectnamedel = firstnameEditText.getText().toString();
                if (projectnamedel.matches(""))
                    new AlertDialog.Builder(UpdateProjectDetails.this )
                            .setMessage( "Missing Project Name" ).show();
                else
                {
                    delfromDB();
                }

            }
        });
        Button  viewButton=(Button) findViewById(R.id.viewdetails);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText firstnameEditText = (EditText) findViewById(R.id.projectnameupd);
                String projectnamesrc = firstnameEditText.getText().toString();
                if (projectnamesrc.matches("")){
                    new AlertDialog.Builder(UpdateProjectDetails.this )
                            .setMessage( "Missing Project Name" ).show();
                }
                else
                {
                    readFromDB();
                }

            }
        });
        Button  updatebotton=(Button) findViewById(R.id.updatproj);
      updatebotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstnameEditText = (EditText) findViewById(R.id.projectnameupd);
                String projectnameupd = firstnameEditText.getText().toString();
                if (projectnameupd.matches(""))
                    new AlertDialog.Builder(UpdateProjectDetails.this )
                            .setMessage( "Missing Project Name" ).show();
                else
                {
                    updateDB();
                }

            }
        });
    }
    private void updateDB() {
        try {
            CardView cav;
            cav = (CardView)findViewById(R.id.cav);
            cav.setVisibility(View.INVISIBLE);
            EditText firstnameEditText = (EditText) findViewById(R.id.projectnameupd);
            EditText originaldurationEditText = (EditText) findViewById(R.id.duration);
           EditText lastnameEditText = (EditText) findViewById(R.id.value);
            EditText employedEditText = (EditText) findViewById(R.id.finish);
            EditText jobDescEditText = (EditText) findViewById(R.id.tmanhours);
            EditText dobedittext = (EditText) findViewById(R.id.startdate);
            String projectnameupd = firstnameEditText.getText().toString();
            String projduration = originaldurationEditText.getText().toString();
            String projvalue = lastnameEditText.getText().toString();
            String projfinish = employedEditText.getText().toString();
            String projmanhours = jobDescEditText.getText().toString();
            String projstart = dobedittext.getText().toString();
            SQLiteDatabase database = new MyprojectHandler(this).getReadableDatabase();
            final String SELECT_EMPLOYEE_WITH_EMPLOYER = "SELECT * " +
                    "FROM " + TABLE_CONTACTS + " WHERE " + KEY_NAME + " like ?";
            String[] selectionArgs = {"%" + projectnameupd + "%"};

            Cursor cursor = database.rawQuery(SELECT_EMPLOYEE_WITH_EMPLOYER, selectionArgs);
            if (cursor != null)
                cursor.moveToFirst();
          Project dBprojects = new Project(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_PRICE)),
                    cursor.getString(cursor.getColumnIndex(KEY_START)),

                    cursor.getString(cursor.getColumnIndex(KEY_MANHOURS)),
                    cursor.getString(cursor.getColumnIndex(KEY_FINISH)));
            if (projvalue.matches("")){

                dBprojects.setprojvalue(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
            }
            else
            {
                dBprojects.setprojvalue(projvalue);
            }
         /*   if (projduration.matches("")){
                dBprojects.setduration(cursor.getString(cursor.getColumnIndex(ORIGINAL_DURATION)));
           }
            else
            {
                dBprojects.setduration(projduration);
            }*/
            if (projmanhours.matches("")){
                dBprojects.setprojmanhours(cursor.getString(cursor.getColumnIndex(KEY_MANHOURS)));

            }
            else
            {
                dBprojects.setprojmanhours(projmanhours);
            }
            if (projstart.matches("")){
                dBprojects.setstartdate(cursor.getString(cursor.getColumnIndex(KEY_START)));

            }
            else
            {
                dBprojects.setstartdate(projstart);
            }
            if (projfinish.matches("")){
                dBprojects.setfinishdate(cursor.getString(cursor.getColumnIndex(KEY_FINISH)));

            }
            else{
                dBprojects.setfinishdate(projfinish);
            }
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, dBprojects.getprojName());
            values.put(KEY_PRICE, dBprojects.getpvalue());
          //  values.put(ORIGINAL_DURATION, dBprojects.getduration());
            values.put(KEY_MANHOURS, dBprojects.getmanhours());
            values.put(KEY_START,dBprojects.getstartdate());
            values.put(KEY_FINISH,dBprojects.getfinishdate());
            database.update(TABLE_CONTACTS, values, KEY_NAME + " = ?", new String[]{String.valueOf(dBprojects.getprojName())});
            Toast toast=  Toast.makeText(getBaseContext(), "Project " + dBprojects.getprojName()+" Updated Successfuly", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();


        }
        catch (Exception e) {
            Log.e(TAG, "Error", e);
            Toast toast= Toast.makeText(this, "Wrong or Missing Data", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }

    }

    private void delfromDB() {
        try {
            CardView cav;
            cav = (CardView)findViewById(R.id.cav);
            cav.setVisibility(View.INVISIBLE);
            EditText firstnameEditText = (EditText) findViewById(R.id.projectnameupd);
            SQLiteDatabase database = new MyprojectHandler(this).getReadableDatabase();
            String projectnameupd = firstnameEditText.getText().toString();
            final String SELECT_EMPLOYEE_WITH_EMPLOYER = "SELECT * " +
                    "FROM " + TABLE_CONTACTS + " WHERE " + KEY_NAME + " like ?";
            String[] selectionArgs = {"%" + projectnameupd + "%"};

            Cursor cursor = database.rawQuery(SELECT_EMPLOYEE_WITH_EMPLOYER, selectionArgs);
            if (cursor != null)
                cursor.moveToFirst();
            Project dBprojects = new Project(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_PRICE)),
                    cursor.getString(cursor.getColumnIndex(KEY_START)),

                    cursor.getString(cursor.getColumnIndex(KEY_MANHOURS)),
                    cursor.getString(cursor.getColumnIndex(KEY_FINISH)));
            database.delete(TABLE_CONTACTS, KEY_NAME + " = ?",
                    new String[]{String.valueOf(dBprojects.getprojName())});
         //   database.close();
          Toast toast=  Toast.makeText(getBaseContext(), "Project " + dBprojects.getprojName()+" Deleted Successfuly", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
        catch (Exception e) {
            Log.e(TAG, "Error", e);
            Toast toast=  Toast.makeText(this, "Insert Correct Project Name", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return;
        }
    }

    private void readFromDB() {
        try{
            CardView cav;
            TextView pname;
            TextView pstart;
            TextView pfinish;
            TextView pmanhours;
            TextView pvalue;
            TextView pdur;
            cav = (CardView)findViewById(R.id.cav);
            pname = (TextView)findViewById(R.id.proj_name);
            pstart = (TextView)findViewById(R.id.Start_date);
            pfinish = (TextView)findViewById(R.id.Finish_date);
            pvalue = (TextView)findViewById(R.id.Proj_value);
            pdur = (TextView)findViewById(R.id.Proj_dur);
            pmanhours = (TextView)findViewById(R.id.Proj_manhours);
        EditText firstnameEditText=(EditText) findViewById(R.id.projectnameupd);
        String projectnameupd = firstnameEditText.getText().toString();
            DecimalFormat df= new DecimalFormat("#,###,###,###");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        SQLiteDatabase database = new MyprojectHandler(this).getReadableDatabase();
            final String SELECT_EMPLOYEE_WITH_EMPLOYER = "SELECT * " +
                    "FROM " + TABLE_CONTACTS + " WHERE " + KEY_NAME + " like ?";
    String[] selectionArgs = {"%" + projectnameupd + "%"};
    Cursor cursor = database.rawQuery(SELECT_EMPLOYEE_WITH_EMPLOYER, selectionArgs);
    if (cursor != null)
        cursor.moveToFirst();
    Project dBprojects =new Project(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),
            cursor.getString(cursor.getColumnIndex(KEY_NAME)),
            cursor.getString(cursor.getColumnIndex(KEY_PRICE)),
            cursor.getString(cursor.getColumnIndex(KEY_START)),
            cursor.getString(cursor.getColumnIndex(KEY_MANHOURS)),
            cursor.getString(cursor.getColumnIndex(KEY_FINISH)));
    calendar1.setTimeInMillis(cursor.getLong(
            cursor.getColumnIndexOrThrow(KEY_START)));
    calendar2.setTimeInMillis(cursor.getLong(
            cursor.getColumnIndexOrThrow(KEY_FINISH)));
      //     detailsshow.setVisibility(View.INVISIBLE);
   /* detailsshow.setText("Project Name: " + sampleDBContract.getprojName().toString() + "\n" +
            "Start Date: " +  new SimpleDateFormat("dd/MM/yyyy").format(calendar1.getTime()) + "\n" +
            "Finish Date: " + new SimpleDateFormat("dd/MM/yyyy").format(calendar2.getTime()) + "\n" +
            "Project Value: " + sampleDBContract.getvalue().toString() + "\n" +
            "Project Manhours: " + sampleDBContract.getmanhours().toString() + "\n" +
            "Project Duration: " + sampleDBContract.getduration().toString());*/
            cav.setVisibility(View.INVISIBLE);
            pname.setText("Project Name: " + dBprojects.getprojName().toString() );
            pstart.setText( "Start Date: " +  dBprojects.getstartdate());
            pfinish.setText("Finish Date: " + dBprojects.getfinishdate());
            //pstart.setText( "Start Date: " +  new SimpleDateFormat("dd/MM/yyyy").format(calendar1.getTime()));
          //  pfinish.setText("Finish Date: " + new SimpleDateFormat("dd/MM/yyyy").format(calendar2.getTime()));
            pvalue.setText("Project Value: " +df.format(Double.valueOf(dBprojects.getpvalue().toString()))+"   "+"U.S.D");
          //  pdur.setText( "Project Duration: " + dBprojects.getduration().toString()+"  "+"Months");
            pmanhours.setText( "Project Manhours: " +df.format( Double.valueOf(dBprojects.getmanhours().toString()))+"  "+"M.H");
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));

            // Setting Dialog Title
            alertDialog.setTitle("Projet Details");
            // Setting Dialog Message
            alertDialog.setMessage(pname.getText()+"\n"+pstart.getText()+
            "\n"+pfinish.getText()+"\n"+pvalue.getText()+"\n"+pmanhours.getText());
            alertDialog.show();
           // database.close();
}
catch (Exception e) {
    Log.e(TAG, "Error", e);
    Toast toast= Toast.makeText(this, "Insert Correct Project Name ", Toast.LENGTH_LONG);
    toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
    toast.show();
    return;
}
    }



}
