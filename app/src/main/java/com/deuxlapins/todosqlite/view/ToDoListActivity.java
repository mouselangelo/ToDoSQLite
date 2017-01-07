package com.deuxlapins.todosqlite.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.deuxlapins.todosqlite.R;
import com.deuxlapins.todosqlite.entity.ToDoItem;
import com.deuxlapins.todosqlite.model.ModelFactory;
import com.deuxlapins.todosqlite.model.ToDoModel;

public class ToDoListActivity extends AppCompatActivity {

    private ListView toDoList;
    private ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        toDoList = (ListView) findViewById(R.id.todoList);
        registerForContextMenu(toDoList);
        refresh();
    }

    private void refresh() {
        ToDoModel model = ModelFactory.getModel();
        adapter = new ToDoListAdapter(this, model.getItems());
        toDoList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuAddTask) {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivityForResult(intent, AddTaskActivity.ADD_TASK_REQUEST);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.todoList) {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(R.string.todo_list_context_memu_title);

            String[] menuItems = getResources().getStringArray(R.array.todolist_context_menu_options);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }

            int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;

            ToDoItem item = (ToDoItem) adapter.getItem(position);

            String doneOption = item.isDone() ? "Mark as not done" : "Mark as done";
            menu.add(Menu.NONE, 2, 2, doneOption);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AddTaskActivity.ADD_TASK_REQUEST) {
            if(resultCode == RESULT_OK) {
                // a new task was added, do refresh
                refresh();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
