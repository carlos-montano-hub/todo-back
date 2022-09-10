package com.encora.todoback.repository;

import com.encora.todoback.model.Priority;
import com.encora.todoback.model.TodoItem;
import com.encora.todoback.dataBase.InMemoryDB;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Repository
public class InMemoryToDoRepository implements ToDoRepository {

    private InMemoryDB inMemoryDB = InMemoryDB.getInstance();

    public InMemoryToDoRepository() {
    }

    @Override
    public Optional<TodoItem> getById(Integer id) {

        if (inMemoryDB.getTodoItems().get(id) != null) return Optional.of(inMemoryDB.getTodoItems().get(id));

        return Optional.empty();

    }

    @Override
    public Set<TodoItem> getAll() {
        return new HashSet<>(inMemoryDB.getTodoItems().values());
    }

    @Override
    public void deleteById(Integer id) {
        inMemoryDB.getTodoItems().remove(id);
    }

    @Override
    public void create(TodoItem todoItem) {

        inMemoryDB.getTodoItems().put(todoItem.getId(), todoItem);
    }


    @Override
    public void update(TodoItem todoItem) {
        inMemoryDB.getTodoItems().put(todoItem.getId(), todoItem);
    }

    @Override
    public void deleteAll() {
        inMemoryDB.getTodoItems().clear();
    }
}
