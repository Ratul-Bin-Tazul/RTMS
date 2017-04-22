package com.drbt.onlinedatacollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SAMSUNG on 2/21/2017.
 */

public class dbOperation extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "test_database.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + internalDatabase.internalDatabaseEntry.TABLE_NAME + " (" +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_DNSO + " TEXT," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_DISTRICT + " TEXT," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_YEAR + " INTEGER," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_MONTH + " TEXT," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_FIRST_LINE + " INTEGER," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_FRONT_LINE + " INTEGER," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_MALE + " INTEGER," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_FEMALE + " INTEGER," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI + " INTEGER," +
                    internalDatabase.internalDatabaseEntry.COLUMN_NAME_SYNC_STATE + " BOOLEAN"+");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + internalDatabase.internalDatabaseEntry.TABLE_NAME;

    public dbOperation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public void addInformation(SQLiteDatabase db,int id,String DNSOName,String district,int year,String month,int firstLine,
                               int frontLine,int male,int female, int anthropocentric,boolean synced) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_ID,id);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_DNSO,DNSOName);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_DISTRICT,district);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_YEAR,year);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_MONTH,month);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FIRST_LINE,firstLine);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FRONT_LINE,frontLine);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_MALE,male);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FEMALE,female);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI,anthropocentric);
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_SYNC_STATE,synced);

        db.insert(internalDatabase.internalDatabaseEntry.TABLE_NAME,null,contentValues);

    }

    public Cursor getInfo(SQLiteDatabase db) {

        String[] projections = {internalDatabase.internalDatabaseEntry.COLUMN_NAME_DNSO,internalDatabase.internalDatabaseEntry.COLUMN_NAME_DISTRICT,
                internalDatabase.internalDatabaseEntry.COLUMN_NAME_YEAR,internalDatabase.internalDatabaseEntry.COLUMN_NAME_MONTH,
                internalDatabase.internalDatabaseEntry.COLUMN_NAME_FIRST_LINE,internalDatabase.internalDatabaseEntry.COLUMN_NAME_FRONT_LINE,
                internalDatabase.internalDatabaseEntry.COLUMN_NAME_MALE,internalDatabase.internalDatabaseEntry.COLUMN_NAME_FEMALE,
                internalDatabase.internalDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI,internalDatabase.internalDatabaseEntry.COLUMN_NAME_SYNC_STATE};

        Cursor cursor = db.query(internalDatabase.internalDatabaseEntry.TABLE_NAME,projections,null,null,null,null,null);


        return cursor;
    }

    public Cursor getFullInfo(SQLiteDatabase db) {
        String[] projections = {internalDatabase.internalDatabaseEntry.COLUMN_NAME_ID,internalDatabase.internalDatabaseEntry.COLUMN_NAME_DNSO,internalDatabase.internalDatabaseEntry.COLUMN_NAME_DISTRICT,
                internalDatabase.internalDatabaseEntry.COLUMN_NAME_YEAR,internalDatabase.internalDatabaseEntry.COLUMN_NAME_MONTH,
                internalDatabase.internalDatabaseEntry.COLUMN_NAME_FIRST_LINE,internalDatabase.internalDatabaseEntry.COLUMN_NAME_FRONT_LINE,
                internalDatabase.internalDatabaseEntry.COLUMN_NAME_MALE,internalDatabase.internalDatabaseEntry.COLUMN_NAME_FEMALE,
                internalDatabase.internalDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI,internalDatabase.internalDatabaseEntry.COLUMN_NAME_SYNC_STATE};

        Cursor cursor = db.query(internalDatabase.internalDatabaseEntry.TABLE_NAME,projections,null,null,null,null,null);


        return cursor;
    }

    public void updateLocalDatabase(SQLiteDatabase db,String id,boolean synced) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(internalDatabase.internalDatabaseEntry.COLUMN_NAME_SYNC_STATE,synced);

        String selection = internalDatabase.internalDatabaseEntry.COLUMN_NAME_ID+" = ?";
        String[] selectionArgs = {id};
        db.update(internalDatabase.internalDatabaseEntry.TABLE_NAME,contentValues,selection,selectionArgs);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
