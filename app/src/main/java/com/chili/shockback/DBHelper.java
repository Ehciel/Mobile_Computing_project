package com.chili.shockback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "feedback.db";
    private static final String TABLE_NAME = "feedback_table";

    // Column names
    private static final String COL_1 = "ID";
    private static final String COL_2 = "satisfaction";
    private static final String COL_3 = "reliability";
    private static final String COL_4 = "responsiveness";
    private static final String COL_5 = "customer_service";
    private static final String COL_6 = "billing_process";
    private static final String COL_7 = "communication";
    private static final String COL_8 = "suggestions";
    private static final String COL_9 = "comments";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table for storing feedback
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "satisfaction INTEGER, reliability TEXT, responsiveness TEXT, customer_service TEXT, " +
                "billing_process TEXT, communication TEXT, suggestions TEXT, comments TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert feedback data
    public boolean insertFeedback(int satisfaction, String reliability, String responsiveness, String customerService,
                                  String billingProcess, String communication, String suggestions, String comments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, satisfaction);
        contentValues.put(COL_3, reliability);
        contentValues.put(COL_4, responsiveness);
        contentValues.put(COL_5, customerService);
        contentValues.put(COL_6, billingProcess);
        contentValues.put(COL_7, communication);
        contentValues.put(COL_8, suggestions);
        contentValues.put(COL_9, comments);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Returns true if insert was successful
    }

    // Get all feedbacks
    public Cursor getAllFeedbacks() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
