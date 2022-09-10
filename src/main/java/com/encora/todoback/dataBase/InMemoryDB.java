package com.encora.todoback.dataBase;

import com.encora.todoback.model.TodoItem;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDB {
    private static InMemoryDB instance;
    private final Map<Integer, TodoItem> todoItems = new HashMap<>();

    private InMemoryDB() {
    }

    public static InMemoryDB getInstance() {
        if (instance == null) {
            instance = new InMemoryDB();
        }
        return instance;
    }

    public Map<Integer, TodoItem> getTodoItems() {
        return todoItems;
    }

    @Override
    public String toString() {
        return "InMemoryDB{" +
                "todoItems=" + todoItems +
                '}';
    }
}
