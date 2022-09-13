package com.encora.todoback.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonSerialize
@JsonDeserialize
public class TodoItem implements Comparable<TodoItem> {


    private Integer id;
    private String name;
    private LocalDate dueDate;
    private Boolean done;
    private LocalDateTime createDate;
    private LocalDateTime doneDate;
    private Duration timeToComplete;
    private Priority priority;

    public TodoItem() {
    }

    public TodoItem(Integer id, String name, Boolean done, LocalDateTime createDate, Priority priority) {
        this.id = id;
        this.name = name;
        this.done = done;
        this.createDate = createDate;
        this.priority = priority;
    }

    public TodoItem(Integer id, String name, LocalDate dueDate, Boolean done, LocalDateTime createDate, Priority priority) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.done = done;
        this.createDate = createDate;
        this.priority = priority;
    }

    //For Testing only
    public TodoItem(int id, LocalDateTime createDate) {
        this.id = id;
        this.createDate = createDate;
        this.done = false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return Objects.equals(id, todoItem.id);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", done=" + done +
                ", createDate=" + createDate +
                ", doneDate=" + doneDate +
                ", timeToComplete=" + timeToComplete +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(TodoItem o) {
        return this.id.compareTo(o.id);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public Duration getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(Duration timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}