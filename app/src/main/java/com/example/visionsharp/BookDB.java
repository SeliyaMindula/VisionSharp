package com.example.visionsharp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookDB extends SQLiteOpenHelper {
    public static final int VERSION = 2;
    public static final String Database_NAME = "BookDB";

    public BookDB(@Nullable Context context) {
        super(context, Database_NAME, null, VERSION);
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
            "CREATE TABLE " + BookProfile.Res.TABLE_NAME + " (" +
                    BookProfile.Res._ID + " INTEGER PRIMARY KEY," +
                    BookProfile.Res.COL_1 + " TEXT," +
                    BookProfile.Res.COL_2 + " TEXT," +
                    BookProfile.Res.COL_3 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BookProfile.Res.TABLE_NAME;


    public long addInfo (String date, String doctor, String phone){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookProfile.Res.COL_1, date);
        values.put(BookProfile.Res.COL_2, doctor);
        values.put(BookProfile.Res.COL_3, phone);


        long newRowId = db.insert(BookProfile.Res.TABLE_NAME, null, values);

        return newRowId;

    }

    public Boolean updateInfo (String date, String doctor, String phone){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookProfile.Res.COL_1, date);
        values.put(BookProfile.Res.COL_2, doctor);

        String selection = BookProfile.Res.COL_3 + " LIKE ?";
        String[] selectionArgs = { phone };

        int count = db.update(
                BookProfile.Res.TABLE_NAME,
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

    public void deleteInfo (String phonenumber){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = BookProfile.Res.COL_3 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { phonenumber };
        // Issue SQL statement.
        int deletedRows = db.delete(BookProfile.Res.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllInfo (String phonenumber){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                BookProfile.Res.COL_1,
                BookProfile.Res.COL_2,
                BookProfile.Res.COL_3,

        };

        String selection = BookProfile.Res.COL_3 + " LIKE ?";
        String[] selectionArgs = { phonenumber };

        String sortOrder =
                BookProfile.Res.COL_3 + " ASC";

        Cursor cursor = db.query(
                BookProfile.Res.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List Bookinfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String Bdate = cursor.getString(cursor.getColumnIndexOrThrow(BookProfile.Res.COL_1));
            String Bdoctor = cursor.getString(cursor.getColumnIndexOrThrow(BookProfile.Res.COL_2));
            String Bphone = cursor.getString(cursor.getColumnIndexOrThrow(BookProfile.Res.COL_3));

            Bookinfo.add(Bdate);//0
            Bookinfo.add(Bdoctor);//1
            Bookinfo.add(Bphone);//2

        }
        cursor.close();
        return Bookinfo;
    }
}
