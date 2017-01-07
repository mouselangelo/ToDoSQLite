package com.deuxlapins.todosqlite.model;

/**
 * Created by chetan on 08/01/17.
 */

public abstract class ModelFactory {

    private static ToDoModel model;

    private ModelFactory(){}

    public static ToDoModel getModel() {
        if (model != null) {
            return model;
        }

        model = new ToDoModelImpl();
        return model;
    }

}
