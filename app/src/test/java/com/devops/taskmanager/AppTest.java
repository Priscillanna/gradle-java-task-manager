package com.devops.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void shouldAddTaskSuccessfully() {
        Task task = taskService.addTask("Complete Gradle project");

        assertNotNull(task);
        assertEquals(1, task.getId());
        assertEquals("Complete Gradle project", task.getTitle());
        assertFalse(task.isCompleted());
    }

    @Test
    void shouldListAllTasks() {
        taskService.addTask("Learn Java");
        taskService.addTask("Practice Gradle");

        assertEquals(2, taskService.getAllTasks().size());
    }

    @Test
    void shouldCompleteTaskSuccessfully() {
        Task task = taskService.addTask("Write unit tests");

        boolean result = taskService.completeTask(task.getId());

        assertTrue(result);
        assertTrue(task.isCompleted());
    }

    @Test
    void shouldDeleteTaskSuccessfully() {
        Task task = taskService.addTask("Delete this task");

        boolean result = taskService.deleteTask(task.getId());

        assertTrue(result);
        assertTrue(taskService.getAllTasks().isEmpty());
    }

    @Test
    void shouldRejectEmptyTaskTitle() {
        assertThrows(
            IllegalArgumentException.class,
            () -> taskService.addTask(" ")
        );
    }

    @Test
    void shouldReturnFalseForUnknownTaskId() {
        assertFalse(taskService.completeTask(999));
        assertFalse(taskService.deleteTask(999));
    }
}