package com.example.android.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.todolist.models.Todo;

import java.util.ArrayList;

/**
 * Created by HP on 15-Apr-17.
 */

public class TodoListAdapter extends BaseAdapter {

    private ArrayList<Todo> todos;
    private MainActivity act;

    public TodoListAdapter(MainActivity act) {
        this.act = act;
    }

    public void updateTodos (ArrayList<Todo> newListOfTodos) {
        todos = newListOfTodos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Todo getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = li.inflate(android.R.layout.simple_list_item_1, null);

        //((TextView) rootView.findViewById(android.R.id.text1)).setText(getItem(position).getTask());

        final Todo thisTodo = getItem(position);
        String task = thisTodo.getTask();
        TextView tvTask = (TextView) rootView.findViewById(android.R.id.text1);
        tvTask.setText(task);
        if (thisTodo.isDone()) {
            tvTask.setTextColor(Color.GRAY);
            tvTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thisTodo.setDone(false);
                    act.setTodoDone(thisTodo);
                }
            });
        } else {
            tvTask.setTextColor(Color.BLACK);
            tvTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thisTodo.setDone(true);
                    act.setTodoDone(thisTodo);
                }
            });
        }


        return rootView;
    }
}