package com.encora.todoback.service;

import com.encora.todoback.model.TodoItem;
import com.encora.todoback.repository.InMemoryToDoRepository;
import com.encora.todoback.repository.ToDoRepository;
import com.encora.todoback.service.exceptions.MaxNameLengthException;
import com.encora.todoback.service.exceptions.NullRequiredParameterException;
import com.encora.todoback.service.exceptions.ParameterMismatchException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InsertTodosService {


    private ToDoRepository repository = new InMemoryToDoRepository();

    public Boolean postTodo(TodoItem todoItem) throws MaxNameLengthException, NullRequiredParameterException, ParameterMismatchException {
        System.out.println(todoItem);

        Validation.validate(todoItem);

        todoItem.setCreateDate(LocalDateTime.now());
        repository.create(todoItem);

        return true;


    }


}
