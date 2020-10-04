package com.example.visionsharp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class PDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "PaymentDB.db";

    public PDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PDetails.Payments.TABLE_NAME + " (" +
                    PDetails.Payments._ID + " INTEGER PRIMARY KEY," +
                    PDetails.Payments.COLUMN_1 + " TEXT," +
                    PDetails.Payments.COLUMN_2 + " TEXT," +
                    PDetails.Payments.COLUMN_3 + " TEXT," +
                    PDetails.Payments.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PDetails.Payments.TABLE_NAME;

    public long addInfo (String holdername, String cardnum, String exdate, String cvvnum){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PDetails.Payments.COLUMN_1, holdername);
        values.put(PDetails.Payments.COLUMN_2, cardnum);
        values.put(PDetails.Payments.COLUMN_3, exdate);
        values.put(PDetails.Payments.COLUMN_4, cvvnum);

        long newRowId = db.insert(PDetails.Payments.TABLE_NAME, null, values);

        return newRowId;
    }

    public Boolean updateInfo (String holdername, String cardnum, String exdate, String cvvnum){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PDetails.Payments.COLUMN_2, cardnum);
        values.put(PDetails.Payments.COLUMN_3, exdate);
        values.put(PDetails.Payments.COLUMN_4, cvvnum);

        String selection = PDetails.Payments.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { holdername };

        int count = db.update(
                PDetails.Payments.TABLE_NAME,
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

    public void deleteInfo (String holdername){

        SQLiteDatabase db = getWritableDatabase();

        String selection = PDetails.Payments.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { holdername };
        int deletedRows = db.delete(PDetails.Payments.TABLE_NAME, selection, selectionArgs);


    }

    public List readAllInfo (String holdername){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                PDetails.Payments.COLUMN_1,
                PDetails.Payments.COLUMN_2,
                PDetails.Payments.COLUMN_3,
                PDetails.Payments.COLUMN_4
        };

        String selection = PDetails.Payments.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { holdername };

        String sortOrder =
                PDetails.Payments.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                PDetails.Payments.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List PaymenetDet = new ArrayList<>();
        while(cursor.moveToNext()) {
            String hname = cursor.getString(cursor.getColumnIndexOrThrow(PDetails.Payments.COLUMN_1));
            String cardno = cursor.getString(cursor.getColumnIndexOrThrow(PDetails.Payments.COLUMN_2));
            String edate = cursor.getString(cursor.getColumnIndexOrThrow(PDetails.Payments.COLUMN_3));
            String cvvno = cursor.getString(cursor.getColumnIndexOrThrow(PDetails.Payments.COLUMN_4));
            PaymenetDet.add(hname);
            PaymenetDet.add(cardno);
            PaymenetDet.add(edate);
            PaymenetDet.add(cvvno);
        }
        cursor.close();
        return PaymenetDet;
    }

}
