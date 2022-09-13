package com.encora.todoback.service;


import com.encora.todoback.model.TodoItem;
import com.encora.todoback.repository.InMemoryToDoRepository;
import com.encora.todoback.repository.ToDoRepository;
import com.encora.todoback.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UpdateTodosService {


    private final ToDoRepository repository;

    @Autowired
    public UpdateTodosService(ToDoRepository repository) {
        this.repository = repository;
    }

    //This method should only update the name, dueDate and priority
    public Boolean editTodo(Integer id, TodoItem newTodoItem) throws ParameterMismatchException, MaxNameLengthException, NullRequiredParameterException, IdNotPresentException {

        if (repository.getById(id).isEmpty()) throw new IdNotPresentException("Id does not exists");

        Validation.validate(newTodoItem);

        TodoItem todoItem = repository.getById(id).get();

        if (newTodoItem.getName() != null) todoItem.setName(newTodoItem.getName());

        if (newTodoItem.getPriority() != null) todoItem.setPriority(newTodoItem.getPriority());

        todoItem.setDueDate(newTodoItem.getDueDate());

        repository.update(todoItem);

        return true;
    }

    public void setDone(Integer id) throws BadIdException {

        if (repository.getById(id).isEmpty()) throw new BadIdException("Id not found");

        repository.getById(id).ifPresent(todoItem -> {
                    if (! todoItem.isDone()) {
                        todoItem.setDone(true);
                        todoItem.setDoneDate(LocalDateTime.now());
                        todoItem.setTimeToComplete(Duration.between(todoItem.getCreateDate(), todoItem.getDoneDate()));
                        repository.update(todoItem);
                    }
                }
        );
    }

    public void setUndone(Integer id) throws BadIdException {
        if (repository.getById(id).isEmpty()) throw new BadIdException("Id not found");

        repository.getById(id).ifPresent(todoItem -> {
            if (todoItem.isDone()) {
                todoItem.setDone(false);
                todoItem.setDoneDate(null);
                todoItem.setTimeToComplete(null);
                repository.update(todoItem);
            }
        });
    }

    public void deleteById(Integer id) throws BadIdException {
        if (repository.getById(id).isEmpty()) throw new BadIdException("Id not found");

        repository.deleteById(id);
    }


}
