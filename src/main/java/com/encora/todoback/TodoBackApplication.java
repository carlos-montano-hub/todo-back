package com.encora.todoback;

import com.encora.todoback.model.Priority;
import com.encora.todoback.model.TodoItem;
import com.encora.todoback.repository.InMemoryToDoRepository;
import com.encora.todoback.repository.ToDoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class TodoBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoBackApplication.class, args);
        ToDoRepository repository = new InMemoryToDoRepository();


        // Testing data
        for (int i = 1; i <= 12; i++) {
            repository.create(new TodoItem(i, "test", LocalDate.now().plusDays(i), false, LocalDateTime.now(), Priority.LOW));
        }


    }

}
