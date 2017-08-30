package com.example.android.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.todolist.db.tables.TodoTable;

/**
 * Created by HP on 15-Apr-17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "todo.db";
    public static final int DB_VER = 2;

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoTable.CMD_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL(TodoTable.UPGRADE_ADD_DONE);
        }

    }
}
