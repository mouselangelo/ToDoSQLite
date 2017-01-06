package com.deuxlapins.todosqlite.entity;

import java.util.Collections;
import java.util.List;

/**
 * Created by chetan on 06/01/17.
 */

public class ToDo implements Comparable<ToDo> {

    private ToDoGroup timeGroup;
    private List<ToDoItem> todoItems;

    public ToDo(ToDoGroup timeGroup, List<ToDoItem> todoItems) {
        this.timeGroup = timeGroup;
        Collections.sort(todoItems);
        this.todoItems = todoItems;
    }

    public ToDoGroup getTimeGroup() {
        return timeGroup;
    }

    public List<ToDoItem> getTodoItems() {
        return todoItems;
    }

    @Override
    public int compareTo(ToDo toDo) {
        return this.getTimeGroup().ordinal() - toDo.getTimeGroup().ordinal();
    }
}
