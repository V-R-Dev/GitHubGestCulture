package com.example.conndocumentationtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "gestculture.db";
    private static final String TABLE_NAME = "technicien";
    private static final int DB_VERSION = 1;
    private static final String TAG = "DbHelper";
    private static DbHelper INSTANCE = null;
    public DbHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (INSTANCE==null) {
            INSTANCE = new DbHelper(context);
        }
        return INSTANCE;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: Db-HELPER");
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+"id INTEGER PRIMARY KEY AUTOINCREMENT, idTechnicien TEXT, nameTechnicien TEXT, surnameTechnicien TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: Db-HELPER");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }

    public long insertData(String idTechnicien, String nameTechnicien, String surnameTechnicien) {
        SQLiteDatabase sqLiteDatabase = INSTANCE.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idTechnicien", idTechnicien);
        cv.put("nameTechnicien", nameTechnicien);
        cv.put("surnameTechnicien", surnameTechnicien);
        return sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    public List<String> readData() {
        SQLiteDatabase sqLiteDatabase = INSTANCE.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + TABLE_NAME, null);
        List<String> stringList = new ArrayList<>();
        if(cursor!=null && cursor.moveToFirst()) {
            do {
                stringList.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return stringList;
    }
}
