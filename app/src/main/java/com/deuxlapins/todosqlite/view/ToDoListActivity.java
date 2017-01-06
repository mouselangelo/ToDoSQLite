package com.deuxlapins.todosqlite.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.deuxlapins.todosqlite.R;
import com.deuxlapins.todosqlite.model.ToDoModel;
import com.deuxlapins.todosqlite.model.ToDoModelImpl;

public class ToDoListActivity extends AppCompatActivity {

    private ListView toDoList;
    private ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        toDoList = (ListView) findViewById(R.id.todoList);

        ToDoModel model = new ToDoModelImpl();

        adapter = new ToDoListAdapter(this, model.getItems());

        toDoList.setAdapter(adapter);

    }
}
