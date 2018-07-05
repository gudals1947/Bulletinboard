package com.example.se02.bulletinboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 94_08 on 2017-11-29.
 */

public class MemberDB extends SQLiteOpenHelper{
    static  final String ID="id";
    static  final String PASSWORD="password";
    static  final String NAME="name";
    static  final String DB_NAME="Member.db";
    static  final int DB_VERSION=1;
    static  final String TABLE_NAME="Member";
    public MemberDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+ TABLE_NAME + "(" + ID +" INTEGER PRIMARY KEY, "+ PASSWORD +" TEXT, "+ NAME+" TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists  " + TABLE_NAME);
        onCreate(db);
    }
}
