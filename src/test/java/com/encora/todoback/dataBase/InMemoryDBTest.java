package com.encora.todoback.dataBase;

import com.encora.todoback.model.TodoItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryDBTest {

    @Test
    void singletonTest() {
        InMemoryDB inMemoryDB = InMemoryDB.getInstance();
        InMemoryDB inMemoryDB2 = InMemoryDB.getInstance();

        TodoItem todoItem = new TodoItem(0, LocalDateTime.now());

        inMemoryDB.getTodoItems().put(todoItem.getId(), todoItem);

        assertEquals(inMemoryDB, inMemoryDB2);

        System.out.println(inMemoryDB);
    }
}
