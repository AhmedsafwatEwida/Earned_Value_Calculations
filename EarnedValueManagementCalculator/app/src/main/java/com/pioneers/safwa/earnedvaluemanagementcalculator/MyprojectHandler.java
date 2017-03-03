package com.pioneers.safwa.earnedvaluemanagementcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmad on 09/12/2016.
 */

public class MyprojectHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "projects5_Manager";

    // Contacts table name
    public static final String TABLE_CONTACTS = "proj5";

    // Contacts Table Columns names
    public static final String KEY_ID ="id";
    public static final String KEY_NAME ="name";
    public static final String KEY_PRICE ="price";
    public static final String KEY_START ="start";
    public static final String KEY_MANHOURS="pmanhours";
    public static final String KEY_FINISH ="finish";

    public MyprojectHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }


    @Override


    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+ KEY_PRICE + " INTEGER,"
                + KEY_START + " TEXT," + KEY_MANHOURS + " INTEGER,"
                + KEY_FINISH + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(sqLiteDatabase);

    }
    // Adding new contact

    public void addproject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, project.getprojName()); // Contact Name
        values.put(KEY_PRICE, project.getpvalue());
        values.put(KEY_START,project.getstartdate());
        values.put(KEY_MANHOURS, project.getmanhours());
        values.put(KEY_FINISH, project.getfinishdate());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    public Project getProject(String proname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME,KEY_PRICE,KEY_START,KEY_MANHOURS,KEY_FINISH},KEY_NAME + "=?",
                new String[]{String.valueOf(proname)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Project project = new Project(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),
                cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                cursor.getString(cursor.getColumnIndex(KEY_PRICE)),
                cursor.getString(cursor.getColumnIndex(KEY_START)),
                cursor.getString(cursor.getColumnIndex(KEY_MANHOURS)),
                cursor.getString(cursor.getColumnIndex(KEY_FINISH)));
        return project;
    }

    public String updateproject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, project.getprojName()); // Contact Name
        values.put(KEY_PRICE, project.getpvalue());
        values.put(KEY_START,project.getfinishdate());
        values.put(KEY_MANHOURS,project.getmanhours());
        values.put(KEY_FINISH, project.getstartdate());

        // updating row

        return String.valueOf(db.update(TABLE_CONTACTS, values,KEY_NAME+ " = ?",
                new String[]{String.valueOf(project.getprojName())}));
    }

    // Deleting single contact

    public void deleteproject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,KEY_NAME + " = ?",
                new String[]{String.valueOf(project.getprojName())});
        db.close();
    }
}
