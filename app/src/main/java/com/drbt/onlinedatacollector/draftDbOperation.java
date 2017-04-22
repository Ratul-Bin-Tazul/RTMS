package com.drbt.onlinedatacollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SAMSUNG on 3/17/2017.
 */

public class draftDbOperation extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "draft_database.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + internalDatabase.draftDatabaseEntry.TABLE_NAME + " (" +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    internalDatabase.draftDatabaseEntry.DRAFT_NAME + " TEXT," +
                    internalDatabase.draftDatabaseEntry.DRAFT_TIME + " TEXT," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_DNSO + " TEXT," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_DISTRICT + " TEXT," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_YEAR + " INTEGER," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_MONTH + " TEXT," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_FIRST_LINE + " INTEGER," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_FRONT_LINE + " INTEGER," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_MALE + " INTEGER," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_FEMALE + " INTEGER," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI + " INTEGER," +
                    internalDatabase.draftDatabaseEntry.COLUMN_NAME_SYNC_STATE + " BOOLEAN"+");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + internalDatabase.draftDatabaseEntry.TABLE_NAME;

    public draftDbOperation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public void addInformation(SQLiteDatabase db,String draftName,String draftTime, String id, String DNSOName, String district, String year, String month, String firstLine,
                               String frontLine, String male, String female, String anthropocentric, String synced) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(internalDatabase.draftDatabaseEntry.DRAFT_NAME,draftName);
        contentValues.put(internalDatabase.draftDatabaseEntry.DRAFT_TIME,draftTime);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_ID,id);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_DNSO,DNSOName);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_DISTRICT,district);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_YEAR,year);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_MONTH,month);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_FIRST_LINE,firstLine);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_FRONT_LINE,frontLine);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_MALE,male);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_FEMALE,female);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI,anthropocentric);
        contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_SYNC_STATE,synced);

        db.insert(internalDatabase.draftDatabaseEntry.TABLE_NAME,null,contentValues);

    }

    public Cursor getInfo(SQLiteDatabase db) {

        String[] projections = {internalDatabase.draftDatabaseEntry.DRAFT_NAME,internalDatabase.draftDatabaseEntry.DRAFT_TIME,internalDatabase.draftDatabaseEntry.COLUMN_NAME_DNSO,internalDatabase.draftDatabaseEntry.COLUMN_NAME_DISTRICT,
                internalDatabase.draftDatabaseEntry.COLUMN_NAME_YEAR,internalDatabase.draftDatabaseEntry.COLUMN_NAME_MONTH,
                internalDatabase.draftDatabaseEntry.COLUMN_NAME_FIRST_LINE,internalDatabase.draftDatabaseEntry.COLUMN_NAME_FRONT_LINE,
                internalDatabase.draftDatabaseEntry.COLUMN_NAME_MALE,internalDatabase.draftDatabaseEntry.COLUMN_NAME_FEMALE,
                internalDatabase.draftDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI,internalDatabase.draftDatabaseEntry.COLUMN_NAME_SYNC_STATE};

        Cursor cursor = db.query(internalDatabase.draftDatabaseEntry.TABLE_NAME,projections,null,null,null,null,null);


        return cursor;
    }

    public Cursor getRow(SQLiteDatabase db,String draftName) {

        String selection = internalDatabase.draftDatabaseEntry.DRAFT_NAME+" = ?";
        String[] selectionArgs = {draftName};

        String[] projections = {internalDatabase.draftDatabaseEntry.DRAFT_NAME,internalDatabase.draftDatabaseEntry.DRAFT_TIME,internalDatabase.draftDatabaseEntry.COLUMN_NAME_DNSO,internalDatabase.draftDatabaseEntry.COLUMN_NAME_DISTRICT,
                internalDatabase.draftDatabaseEntry.COLUMN_NAME_YEAR,internalDatabase.draftDatabaseEntry.COLUMN_NAME_MONTH,
                internalDatabase.draftDatabaseEntry.COLUMN_NAME_FIRST_LINE,internalDatabase.draftDatabaseEntry.COLUMN_NAME_FRONT_LINE,
                internalDatabase.draftDatabaseEntry.COLUMN_NAME_MALE,internalDatabase.draftDatabaseEntry.COLUMN_NAME_FEMALE,
                internalDatabase.draftDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI,internalDatabase.draftDatabaseEntry.COLUMN_NAME_SYNC_STATE};

        Cursor cursor = db.query(internalDatabase.draftDatabaseEntry.TABLE_NAME,projections,selection,selectionArgs,null,null,null);


        return cursor;
    }

    public Cursor getDraftNameTime(SQLiteDatabase db) {

        String[] projections = {internalDatabase.draftDatabaseEntry.DRAFT_NAME,internalDatabase.draftDatabaseEntry.DRAFT_TIME};

        Cursor cursor = db.query(internalDatabase.draftDatabaseEntry.TABLE_NAME,projections,null,null,null,null,null);


        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public void deleteDraftRecord(SQLiteDatabase db,String draftName,String draftTime) {

        ContentValues contentValues = new ContentValues();
        //contentValues.put(internalDatabase.draftDatabaseEntry.COLUMN_NAME_SYNC_STATE,synced);

        String selection = internalDatabase.draftDatabaseEntry.DRAFT_NAME+" = ?";
        String[] selectionArgs = {draftName};
        //db.update(internalDatabase.draftDatabaseEntry.TABLE_NAME,contentValues,selection,selectionArgs);
        db.delete(internalDatabase.draftDatabaseEntry.TABLE_NAME,selection,selectionArgs);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
