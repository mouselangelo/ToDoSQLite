package com.deuxlapins.todosqlite.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.deuxlapins.todosqlite.R;
import com.deuxlapins.todosqlite.entity.ToDoItem;
import com.deuxlapins.todosqlite.model.ModelFactory;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {


    public static final int ADD_TASK_REQUEST = 999;


    private EditText txtToDoTitle;
    private DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        txtToDoTitle = (EditText) findViewById(R.id.txtToDo);
        picker = (DatePicker) findViewById(R.id.datePicker);
    }

    public void doCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void doSave(View view) {

        String title = txtToDoTitle.getText().toString();

        if (TextUtils.isEmpty(title)) {
            txtToDoTitle.setError("Must enter a task!");
            return;
        }

        int year = picker.getYear();
        int month = picker.getMonth();
        int day = picker.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);

        ToDoItem item = new ToDoItem(title, calendar.getTime());

        ModelFactory.getModel().add(item);

        setResult(RESULT_OK);
        finish();
    }

}
