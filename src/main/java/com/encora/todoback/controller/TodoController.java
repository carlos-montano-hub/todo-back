package com.encora.todoback.controller;

import com.encora.todoback.model.AvgTime;
import com.encora.todoback.model.ModelSize;
import com.encora.todoback.model.Priority;
import com.encora.todoback.model.TodoItem;
import com.encora.todoback.service.FetchTodosService;
import com.encora.todoback.service.InsertTodosService;
import com.encora.todoback.service.SortBy;
import com.encora.todoback.service.UpdateTodosService;
import com.encora.todoback.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:8080/")
public class TodoController {


    private final FetchTodosService fetchTodosService;
    private final InsertTodosService insertTodosService;

    private final UpdateTodosService updateTodosService;

    @Autowired
    public TodoController(FetchTodosService fetchTodosService, InsertTodosService insertTodosService, UpdateTodosService updateTodosService) {
        this.fetchTodosService = fetchTodosService;
        this.insertTodosService = insertTodosService;
        this.updateTodosService = updateTodosService;
    }

    @GetMapping
    public Optional<List<TodoItem>> getTodos(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "DEFAULT") SortBy sortBy,
                                             @RequestParam(required = false) Boolean filterByDone,
                                             @RequestParam(required = false) String filterByName,
                                             @RequestParam(required = false) Priority filterByPriority) {

        return fetchTodosService.getTodos(page, sortBy, filterByDone, filterByName, filterByPriority);
    }

    @GetMapping("/{id}")
    public Optional<TodoItem> getTodoItem(@PathVariable Integer id) {

        try {
            return fetchTodosService.getTodoItem(id);
        } catch (BadIdException e) {
            return Optional.empty();
        }
    }

    @GetMapping("/avg")
    public AvgTime getTodoItem() {

        return fetchTodosService.getAvgTime();

    }

    @GetMapping("/size")
    public ModelSize getSize(@RequestParam(required = false) Boolean filterByDone,
                             @RequestParam(required = false) String filterByName,
                             @RequestParam(required = false) Priority filterByPriority) {
        return new ModelSize(fetchTodosService.getSize(filterByDone, filterByName, filterByPriority));
    }


    @PostMapping
    public ResponseEntity<String> postTodoItem(@RequestBody TodoItem todoItem) {

        try {
            insertTodosService.postTodo(todoItem);
            return new ResponseEntity<>("Created Todo", HttpStatus.CREATED);
        } catch (ParameterMismatchException |
                 NullRequiredParameterException |
                 MaxNameLengthException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodoItem(@PathVariable Integer id, @RequestBody TodoItem todoItem) {

        if (! Objects.equals(id, todoItem.getId()))
            return new ResponseEntity<>("Ids does not match", HttpStatus.BAD_REQUEST);

        try {
            updateTodosService.editTodo(id, todoItem);
            return new ResponseEntity<>("Updated Todo: " + id, HttpStatus.ACCEPTED);
        } catch (ParameterMismatchException | MaxNameLengthException | NullRequiredParameterException |
                 IdNotPresentException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/{id}/done")
    public ResponseEntity<String> setTodoAsDone(@PathVariable Integer id) {
        try {
            updateTodosService.setDone(id);
            return new ResponseEntity<>("Todo set to Done", HttpStatus.ACCEPTED);
        } catch (BadIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/undone")
    public ResponseEntity<String> setTodoAsUndone(@PathVariable Integer id) {
        try {
            updateTodosService.setUndone(id);
            return new ResponseEntity<>("Todo set to not Done", HttpStatus.ACCEPTED);
        } catch (BadIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Integer id) {
        try {
            updateTodosService.deleteById(id);
            return new ResponseEntity<>("Todo with id: " + id + " deleted", HttpStatus.ACCEPTED);
        } catch (BadIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
