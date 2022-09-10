package com.encora.todoback.repository;

import com.encora.todoback.model.Priority;
import com.encora.todoback.model.TodoItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface ToDoRepository {

    Optional<TodoItem> getById(Integer id);

    Collection<TodoItem> getAll();

    void deleteById(Integer id);

    void create(TodoItem todoItem);

    void update(TodoItem todoItem);

    void deleteAll();
}
