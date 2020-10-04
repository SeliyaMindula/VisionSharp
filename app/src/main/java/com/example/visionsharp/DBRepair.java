package com.example.visionsharp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBRepair extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "Repair.db";
    public DBRepair(Context context) {
        super(context, DB_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Specs.spec.TABLE_NAME + " (" +
                    Specs.spec._ID + " INTEGER PRIMARY KEY," +
                    Specs.spec.COLUMN_1 + " TEXT," +
                    Specs.spec.COLUMN_2 + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Specs.spec.TABLE_NAME;


    public long makeReserve (String type, String phone){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Specs.spec.COLUMN_1, type);
       values.put(Specs.spec.COLUMN_2, phone);


        long newRowId = db.insert(Specs.spec.TABLE_NAME, null, values);

        return newRowId;

    }

    public Boolean specUpdate (String type, String phone){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Specs.spec.COLUMN_1, type);
        values.put(Specs.spec.COLUMN_2, phone);

        String selection = Specs.spec.COLUMN_2 + " LIKE ?";
        String[] selectionArgs = { phone };

        int count = db.update(
                Specs.spec.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >=1 ) {
            return true;
        }
        else {
            return false;
        }

    }

    public void specDelete (String phonenumber){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = Specs.spec.COLUMN_2 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { phonenumber };
        // Issue SQL statement.
        int deletedRows = db.delete(Specs.spec.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllSpecs (String phone){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                Specs.spec.COLUMN_1,
                Specs.spec.COLUMN_2
        };

        String selection = Specs.spec.COLUMN_2 + " LIKE ?";
        String[] selectionArgs = { phone };

        String sortOrder =
                Specs.spec.COLUMN_2 + " ASC";

        Cursor cursor = db.query(
                Specs.spec.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List specInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String type = cursor.getString(cursor.getColumnIndexOrThrow(Specs.spec.COLUMN_1));
            String phoneNo = cursor.getString(cursor.getColumnIndexOrThrow(Specs.spec.COLUMN_2));

            specInfo.add(type);//0
            specInfo.add(phoneNo);//1

        }
        cursor.close();
        return specInfo;
    }

}
