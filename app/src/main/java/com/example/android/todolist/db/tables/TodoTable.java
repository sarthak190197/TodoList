package com.example.android.todolist.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.todolist.models.Todo;

import java.util.ArrayList;

import static com.example.android.todolist.db.DbConsts.COMMA;
import static com.example.android.todolist.db.DbConsts.CREATE_TABLE_INE;
import static com.example.android.todolist.db.DbConsts.LBR;
import static com.example.android.todolist.db.DbConsts.TYPE_AI;
import static com.example.android.todolist.db.DbConsts.TYPE_INT;
import static com.example.android.todolist.db.DbConsts.TYPE_PK;
import static com.example.android.todolist.db.DbConsts.*;

/**
 * Created by HP on 15-Apr-17.
 */

public class TodoTable {

    public static final String TAG = "TODO";

    public static final String TABLE_NAME = "todos";

    interface Columns {
        String ID = "id";
        String TASK = "task";
        String DONE = "done";

    }




    public static final String CMD_CREATE_TABLE =
            CREATE_TABLE_INE
                    + TABLE_NAME
                    + LBR
                    + Columns.ID + TYPE_INT + TYPE_PK + TYPE_AI
                    + COMMA
                    + Columns.TASK + TYPE_TEXT
                    + COMMA
                    + Columns.DONE + TYPE_BOOL
                    + RBR
                    + SEMICOL;

    public static final String UPGRADE_ADD_DONE =
            ALTER_TABLE + TABLE_NAME + ADD + Columns.DONE + TYPE_BOOL + SEMICOL;

    public static void addTask (SQLiteDatabase db, Todo newTodo) {
        if (db.isReadOnly()) {
            Log.w(TAG, "addTask: Database is note writeable");
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(Columns.TASK, newTodo.getTask());
        cv.put(Columns.DONE, false);

        db.insert(
                TABLE_NAME,
                null,
                cv
        );
    }

    public static ArrayList<Todo> fetchTodos (SQLiteDatabase db) {

        Cursor c = db.query(
                TABLE_NAME,
                new String[] {Columns.ID, Columns.TASK, Columns.DONE},
                null, null, null, null, null
        );
        ArrayList<Todo> todos = new ArrayList<>();
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex(Columns.ID);
            int taskColIndex = c.getColumnIndex(Columns.TASK);
            int doneColIndex = c.getColumnIndex(Columns.DONE);
            do {
                Todo nextTodo = new Todo(
                        c.getInt(idColIndex),
                        c.getString(taskColIndex),
                        c.getInt(doneColIndex) == 1
                );
                todos.add(nextTodo);
            } while ((c.moveToNext()));
        }
        c.close();

        return todos;
    }

    public static void setDone (SQLiteDatabase db, Todo todoToDo) {

        ContentValues cv = new ContentValues();
        Log.d(TAG, "setDone: value we got = " + todoToDo.isDone());
        cv.put(Columns.DONE, todoToDo.isDone());


        db.update(
                TABLE_NAME,
                cv,
                Columns.ID + " = ?",
                new String[]{String.valueOf(todoToDo.getId())}
        );

    }
}
