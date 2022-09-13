package com.encora.todoback.service;

import com.encora.todoback.model.AvgTime;
import com.encora.todoback.model.Priority;
import com.encora.todoback.model.TodoItem;
import com.encora.todoback.repository.InMemoryToDoRepository;
import com.encora.todoback.repository.ToDoRepository;
import com.encora.todoback.service.exceptions.BadIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FetchTodosService {


    private final ToDoRepository repository;


    @Autowired
    public FetchTodosService(ToDoRepository repository) {
        this.repository = repository;
    }

    public Optional<List<TodoItem>> getTodos(Integer page, SortBy sortBy, Boolean filterByDone, String filterByName, Priority filterByPriority) {
        Integer elementsPerPage = 10;
        int startIndex = page * elementsPerPage - elementsPerPage;
        int endIndex = page * elementsPerPage - 1;

        List<TodoItem> todoItemList = new ArrayList<>(repository.getAll());
        List<TodoItem> filteredList = new ArrayList<>(todoItemList);

        if (sortBy == SortBy.BOTH_PRIORITY_FIRST) {
            filteredList.sort(Comparator.comparing(TodoItem::getPriority).thenComparing(TodoItem::getDueDate, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(TodoItem::getCreateDate));
        }
        if (sortBy == SortBy.BOTH_DUE_DATE_FIRST) {
            filteredList.sort(Comparator.comparing(TodoItem::getDueDate, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(TodoItem::getPriority).thenComparing(TodoItem::getCreateDate));
        }
        if (sortBy == SortBy.PRIORITY) {
            filteredList.sort(Comparator.comparing(TodoItem::getPriority).thenComparing(TodoItem::getCreateDate));
        }

        if (sortBy == SortBy.DUE_DATE) {
            filteredList.sort(Comparator.comparing(TodoItem::getDueDate, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(TodoItem::getCreateDate));
        }
        if (sortBy == SortBy.DEFAULT) {
            filteredList.sort(Comparator.comparing(TodoItem::getCreateDate));
        }

        if (filterByDone != null) {
            filteredList = filteredList.stream().filter(todoItem -> todoItem.isDone() == filterByDone).collect(Collectors.toList());
        }
        if (filterByName != null) {
            filteredList = filteredList.stream().filter(todoItem ->
                    todoItem.getName().contains(new StringBuilder(filterByName))
            ).collect(Collectors.toList());
        }
        if (filterByPriority != null) {
            filteredList = filteredList.stream().filter(todoItem -> todoItem.getPriority().equals(filterByPriority)).collect(Collectors.toList());
        }
        if (filteredList.size() <= 10) {
            return Optional.of(filteredList);
        }

        if (startIndex >= filteredList.size()) {
            return Optional.empty();
        }
        List<TodoItem> finalList = new ArrayList<>();
        for (int i = startIndex; i <= endIndex; i++) {
            if (i < filteredList.size()) finalList.add(filteredList.get(i));
        }

        return Optional.of(finalList);
    }

    public Optional<TodoItem> getTodoItem(Integer id) throws BadIdException {
        if (repository.getById(id).isEmpty()) throw new BadIdException("Id not found");
        return repository.getById(id);
    }

    public AvgTime getAvgTime() {
        List<TodoItem> todoItemList = new ArrayList<>(repository.getAll());
        AvgTime avgTime = new AvgTime(0, 0, 0, 0);

        todoItemList = todoItemList.stream().filter(TodoItem::isDone).collect(Collectors.toList());

        if (todoItemList.size() > 0) {

            List<Long> avgList = todoItemList.stream().map(todoItem -> todoItem.getTimeToComplete().toSeconds()).toList();

            int avg = 0;
            for (Long minutes : avgList) {
                avg += minutes.intValue();
            }
            avg = avg / avgList.size();

            avgTime.setTimeForAll(avg);


            List<Long> lowList = todoItemList.stream().filter(todoItem -> todoItem.getPriority().equals(Priority.LOW)).map(todoItem -> todoItem.getTimeToComplete().toSeconds()).toList();
            List<Long> mediumList = todoItemList.stream().filter(todoItem -> todoItem.getPriority().equals(Priority.MEDIUM)).map(todoItem -> todoItem.getTimeToComplete().toSeconds()).toList();
            List<Long> highList = todoItemList.stream().filter(todoItem -> todoItem.getPriority().equals(Priority.HIGH)).map(todoItem -> todoItem.getTimeToComplete().toSeconds()).toList();

            if (lowList.size() > 0) {
                avg = 0;
                for (Long minutes : lowList) {
                    avg += minutes.intValue();
                }
                avg = avg / lowList.size();

                avgTime.setTimeForLow(avg);
            }
            if (mediumList.size() > 0) {
                avg = 0;
                for (Long minutes : mediumList) {
                    avg += minutes.intValue();
                }
                avg = avg / mediumList.size();

                avgTime.setTimeForMedium(avg);
            }
            if (highList.size() > 0) {
                avg = 0;
                for (Long minutes : highList) {
                    avg += minutes.intValue();
                }
                avg = avg / highList.size();

                avgTime.setTimeForHigh(avg);
            }
        }
        return avgTime;
    }

    public Integer getSize
            (Boolean filterByDone, String filterByName, Priority filterByPriority) {

        List<TodoItem> filteredList = new ArrayList<>(repository.getAll());


        if (filterByDone != null)
            filteredList = filteredList.stream().filter(todoItem -> todoItem.isDone() == filterByDone).collect(Collectors.toList());

        if (filterByName != null)
            filteredList = filteredList.stream().filter(todoItem -> todoItem.getName().contains(filterByName)).collect(Collectors.toList());

        if (filterByPriority != null)
            filteredList = filteredList.stream().filter(todoItem -> todoItem.getPriority().equals(filterByPriority)).collect(Collectors.toList());

        return filteredList.size();
    }


}
