package com.deuxlapins.todosqlite.entity;

import java.util.Date;

/**
 * Created by chetan on 06/01/17.
 * A simple class to hold a To do item
 */

public class ToDoItem implements Comparable<ToDoItem> {

    private String title;

    private Date dueDate;

    private boolean done;

    public ToDoItem(String title) {
        this.title = title;
    }

    public ToDoItem(String title, Date dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public ToDoItem(String title, Date dueDate, boolean done) {
        this.title = title;
        this.dueDate = dueDate;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public int compareTo(ToDoItem item) {
        long me = this.dueDate != null ? this.dueDate.getTime() : 0;
        long other = item.dueDate != null ? item.dueDate.getTime() : 0;

        if (me == other) {
            return  0;
        } else if (me < other) {
            return  -1;
        } else {
            return  1;
        }
    }
}
