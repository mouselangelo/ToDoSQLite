package com.deuxlapins.todosqlite.model;

import com.deuxlapins.todosqlite.entity.ToDo;
import com.deuxlapins.todosqlite.entity.ToDoItem;

import java.util.List;

/**
 * Created by chetan on 06/01/17.
 */

public interface ToDoModel {

    public List<ToDo> getItems();

    public void add(ToDoItem item);

    public void delete(ToDoItem item);

    public void update(ToDoItem item);

}
