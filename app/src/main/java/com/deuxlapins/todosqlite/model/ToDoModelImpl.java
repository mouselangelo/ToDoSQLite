package com.deuxlapins.todosqlite.model;

import com.deuxlapins.todosqlite.entity.ToDoGroup;
import com.deuxlapins.todosqlite.entity.ToDo;
import com.deuxlapins.todosqlite.entity.ToDoItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chetan on 06/01/17.
 */

public class ToDoModelImpl implements ToDoModel {


    @Override
    public List<ToDo> getItems() {
        return groupToDos(getDummyItems());
    }

    @Override
    public void add(ToDoItem item) {

    }

    @Override
    public void delete(ToDoItem item) {

    }

    @Override
    public void update(ToDoItem item) {

    }

    private List<ToDo> groupToDos(List<ToDoItem> items) {

        Map<ToDoGroup, List<ToDoItem>> allItems = new HashMap<>();

        for (ToDoItem item : items) {
            ToDoGroup group = ToDoGroup.getGroupFor(item);
            List<ToDoItem> groupItems = allItems.get(group);

            if (groupItems == null) {
                groupItems = new ArrayList<>();
                allItems.put(group, groupItems);
            }

            groupItems.add(item);
        }

        List<ToDo> toDos = new ArrayList<>();

        for (ToDoGroup group : allItems.keySet() ) {

            ToDo toDo = new ToDo(group, allItems.get(group));

            toDos.add(toDo);
        }

        Collections.sort(toDos);

        return toDos;
    }


    private static List<ToDoItem> dummies;

    private static List<ToDoItem> getDummyItems() {
        if (dummies != null) {
            return dummies;
        }

        List<ToDoItem> items = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            String title = generateRandomString();

            Date date = generateRandomDate();

            boolean done = generateRandomDoneStatus();

            ToDoItem item = new ToDoItem(title, date, done);

            items.add(item);
        }

        dummies = items;

        return dummies;
    }

    private static String generateRandomString() {
        return  "This is a todo item.";
    }

    private static Date generateRandomDate() {

        Calendar today = Calendar.getInstance();

        int offsetDays = (int) Math.round( Math.random() * 10);
        if (generateRandomDoneStatus()) {
            offsetDays = -offsetDays;
        }

        today.add(Calendar.DATE, offsetDays);

        return today.getTime();
    }

    private static boolean generateRandomDoneStatus() {
        return Math.random() < 0.5;
    }

}
