package com.devops.taskmanager;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task addTask(String title) {
        Task task = new Task(nextId++, title);
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public boolean completeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markCompleted();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    public boolean hasTasks() {
        return !tasks.isEmpty();
    }
}