package com.deuxlapins.todosqlite.entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by chetan on 06/01/17.
 */

public enum ToDoGroup {
    OVERDUE("Overdue"),
    TODAY("Today"),
    TOMORROW("Tomorrow"),
    COMING_UP("Coming Up"),
    SOMEDAY("Someday"),
    DONE("Done");

    private String label;

    ToDoGroup(String label) {
        this.label = label;
    }

    public static ToDoGroup getGroupFor(ToDoItem item) {

        if (item.isDone()) {
            return DONE;
        }

        Date dueDate = item.getDueDate();
        if (dueDate == null) {
            return  SOMEDAY;
        }

        Date today = new Date();

        long timeDiff = dueDate.getTime() - today.getTime();

        long dayDiff = TimeUnit.MILLISECONDS.toDays(timeDiff);

        if (dayDiff < 0) {
            return OVERDUE;
        } else if (dayDiff == 0) {
            return TODAY;
        } else if (dayDiff == 1) {
            return TOMORROW;
        } else if (dayDiff <= 7) {
            return COMING_UP;
        } else {
            return SOMEDAY;
        }
    }

    public String getLabel() {
        return label;
    }
}
