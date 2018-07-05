package com.example.se02.bulletinboard;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 94_08 on 2017-12-04.
 */


public class FullWriteDB extends SQLiteOpenHelper {
    static  final String DB_TITLE="title";
    static  final String DB_CONTENT="content";
    static  final String DB_STUDENTNAME="studentname";
    static  final int DB_VERSION=1;
    static  final String TABLE_NAME2 = "Full";
    static  final String DB_NAME3="full.db";
    public FullWriteDB(Context context) {
        super(context,DB_NAME3, null, DB_VERSION);
    }
    public FullWriteDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+ TABLE_NAME2 +"(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ DB_TITLE +" TEXT, "+ DB_CONTENT + " TEXT, "+
                DB_STUDENTNAME+ " TEXT);";
        Log.v("생성됨",""+query);
        db.execSQL(query) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
