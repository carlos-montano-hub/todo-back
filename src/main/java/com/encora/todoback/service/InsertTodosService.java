package com.encora.todoback.service;

import com.encora.todoback.model.TodoItem;
import com.encora.todoback.repository.ToDoRepository;
import com.encora.todoback.service.exceptions.MaxNameLengthException;
import com.encora.todoback.service.exceptions.NullRequiredParameterException;
import com.encora.todoback.service.exceptions.ParameterMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InsertTodosService {


    private final ToDoRepository repository;

    @Autowired
    public InsertTodosService(ToDoRepository repository) {
        this.repository = repository;
    }

    public void postTodo(TodoItem todoItem) throws MaxNameLengthException, NullRequiredParameterException, ParameterMismatchException {

        Validation.validate(todoItem);

        todoItem.setCreateDate(LocalDateTime.now());
        repository.create(todoItem);
    }
}
