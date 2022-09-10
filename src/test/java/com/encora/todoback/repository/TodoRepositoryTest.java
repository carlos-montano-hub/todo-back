package com.encora.todoback.repository;

import com.encora.todoback.model.Priority;
import com.encora.todoback.model.TodoItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoRepositoryTest {

    ToDoRepository repository = new InMemoryToDoRepository();

    @Test
    void getByIdTest() {
        setup();
        assertTrue(repository.getById(null).isEmpty());
        if (repository.getById(99).isPresent()) {
            assertEquals(repository.getById(99).get().getId(), 99);
        } else {

            fail();
        }

        closeup();

    }

    void setup() {
        repository.create(new TodoItem(99, "name", false, LocalDateTime.now(), Priority.LOW));
        repository.create(new TodoItem(100, "name", false, LocalDateTime.now(), Priority.LOW));
        repository.create(new TodoItem(101, "name", false, LocalDateTime.now(), Priority.LOW));
    }


    void closeup() {
        repository.deleteAll();
    }

    @Test
    void getAllTest() {
        closeup();
        assertEquals(repository.getAll().size(), 0);
        setup();
        assertEquals(repository.getAll().size(), 3);
        closeup();
    }

    @Test
    void deleteByIdTest() {
        closeup();

        assertTrue(repository.getById(99).isEmpty());
        setup();
        assertNotNull(repository.getById(99));
        repository.deleteById(99);
        repository.deleteById(null);
        assertTrue(repository.getById(99).isEmpty());
        closeup();
    }

    @Test
    void createTest() {
        closeup();
        assertTrue(repository.getById(99).isEmpty());
        repository.create(new TodoItem(99, "name", false, LocalDateTime.now(), Priority.LOW));
        assertNotNull(repository.getById(99));
        closeup();
    }


    @Test
    void deleteAllTest() {
        setup();
        repository.deleteAll();
        assertEquals(repository.getAll().size(), 0);
        closeup();
    }
}


