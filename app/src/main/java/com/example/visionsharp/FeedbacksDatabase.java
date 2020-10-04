package com.example.visionsharp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FeedbacksDatabase extends SQLiteOpenHelper {
    public static final int VERSION = 3;
    public static final String DATABASE_NAME = "Feedback.db";
    public static final String TABLE_NAME = "feedback";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FEEDBACK";
    public static final String COL_3 = "RATE";
    public static final String COL_4 = "OTHER";
    public static final String COL_5 = "User";

    public FeedbacksDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FEEDBACK TEXT,RATE TEXT,OTHER TEXT,USER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllData(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

//    public String getRate(String id) {
//        SQLiteDatabase db = getReadableDatabase();
//
//        String rate = null;
//        String[] projection = {
//                COL_3
//        };
//
//        String selection = COL_1 + " LIKE ?";
//        String[] selectionArgs = {id};
//
//        String sortOrder =
//                COL_1 + " ASC";
//
//        Cursor cursor = db.query(
//                TABLE_NAME,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                sortOrder
//        );
//
//
//        while(cursor.moveToNext()) {
//            rate = cursor.getString(cursor.getColumnIndexOrThrow(COL_3));
//        }
//        cursor.close();
//
//        return rate;
//    }




    public boolean inserData(String feedback,String rating,String other,String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,feedback);
        contentValues.put(COL_3,rating);
        contentValues.put(COL_4,other);
        contentValues.put(COL_5,user);
        Long result = db.insert(TABLE_NAME,null,contentValues);


        if(result == -1)
            return false;
        else
            return true;
    }



    public boolean updateData(String id,String rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3,rating);

        int count = db.update(TABLE_NAME, contentValues,"id = ?",new String[] { id });
        if (count >=1 ) {
            return true;
        }
        else {
            return false;
        }
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?",new String[] { id });
    }


}