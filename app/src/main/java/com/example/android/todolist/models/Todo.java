package com.example.android.todolist.models;

/**
 * Created by HP on 15-Apr-17.
 */

public class Todo {
    int id;
    String task;
    boolean done;

    public Todo(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public Todo(int id, String task, boolean done) {
        this.id = id;
        this.task = task;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
