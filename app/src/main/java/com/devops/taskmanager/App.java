package com.devops.taskmanager;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final TaskService taskService = new TaskService();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            System.out.println("================================");
            System.out.println("     GRADLE JAVA TASK MANAGER");
            System.out.println("================================");

            while (running) {
                System.out.println("\n1. Add Task");
                System.out.println("2. View Tasks");
                System.out.println("3. Complete Task");
                System.out.println("4. Delete Task");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        System.out.print("Enter task title: ");
                        String title = scanner.nextLine();

                        try {
                            Task task = taskService.addTask(title);
                            System.out.println("Task added: " + task);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "2":
                        List<Task> tasks = taskService.getAllTasks();

                        if (tasks.isEmpty()) {
                            System.out.println("No tasks available.");
                        } else {
                            tasks.forEach(System.out::println);
                        }
                        break;

                    case "3":
                        System.out.print("Enter task ID to complete: ");
                        handleTaskAction(scanner.nextLine(), true);
                        break;

                    case "4":
                        System.out.print("Enter task ID to delete: ");
                        handleTaskAction(scanner.nextLine(), false);
                        break;

                    case "5":
                        running = false;
                        System.out.println("Thank you for using Task Manager!");
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    private static void handleTaskAction(String input, boolean complete) {
        try {
            int id = Integer.parseInt(input.trim());

            boolean success = complete
                    ? taskService.completeTask(id)
                    : taskService.deleteTask(id);

            if (success) {
                System.out.println(
                        complete ? "Task completed." : "Task deleted."
                );
            } else {
                System.out.println("Task ID not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric task ID.");
        }
    }
}