package com.encora.todoback.service;

import com.encora.todoback.model.TodoItem;
import com.encora.todoback.service.exceptions.MaxNameLengthException;
import com.encora.todoback.service.exceptions.NullRequiredParameterException;
import com.encora.todoback.service.exceptions.ParameterMismatchException;

public class Validation {

    public static void validate(TodoItem todoItem) throws MaxNameLengthException, NullRequiredParameterException, ParameterMismatchException {

        if (todoItem.getId() == null) throw new NullRequiredParameterException("Id required");
        if (todoItem.getPriority() == null) throw new NullRequiredParameterException("Priority required");
        if (todoItem.isDone() == null) throw new NullRequiredParameterException("Done required");

        if (todoItem.isDone() && todoItem.getDoneDate() == null)
            throw new ParameterMismatchException("Done date required if todo is done");
        if (! todoItem.isDone() && todoItem.getDoneDate() != null)
            throw new ParameterMismatchException("Todo not set to done but, set done date");

        if (todoItem.getName() == null) throw new NullRequiredParameterException("Name required");
        if (todoItem.getName().length() > 120)
            throw new MaxNameLengthException("Name length larger than 120 Characters");

    }
}
