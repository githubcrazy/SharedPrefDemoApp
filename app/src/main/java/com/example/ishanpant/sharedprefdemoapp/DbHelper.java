package com.example.ishanpant.sharedprefdemoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.name;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DBName = "register.db";
    private static final int DBVersion = 4;

    private static final String USER_TABLE = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "password";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_EMAIL + " TEXT NOT NULL,"
            + COLUMN_PASS + " TEXT NOT NULL" + ")";


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBName, factory, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "users");
        onCreate(sqLiteDatabase);
    }

    public void addUser(String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASS,password);
        long id = database.insert(USER_TABLE, null, values);
        database.close();
    }

    public boolean getUser(String email, String password) {
        String selectQuery = "select * from " + USER_TABLE + " where " + COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+password+"'";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        database.close();

        return false;
     }
}
