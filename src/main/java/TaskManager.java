import java.util.ArrayList;
import java.util.List;

import Exceptions.ElchinoException;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidInputException;
import Exceptions.EmptyDescriptionException;

public class TaskManager {
    private final List<Task> taskList = new ArrayList<>();

    public void handleCommand(String command) throws ElchinoException {
        String[] parts = command.split(" ", 2);
        String action = parts[0];

        switch (action) {
            case "list" -> printTaskList();
            case "mark" -> {
                int n = Integer.parseInt(parts[1]);
                taskList.get(n - 1).setDone();
                System.out.println("Ok, lo he marcado como hecho:");
                System.out.println(taskList.get(n - 1));
            }
            case "unmark" -> {
                int n = Integer.parseInt(parts[1]);
                taskList.get(n - 1).setNotDone();
                System.out.println("Ok, lo he marcado como deshacer:");
                System.out.println(taskList.get(n - 1));
            }
            case "todo" -> addTodo(parts[1]);
            case "deadline" -> addDeadline(parts[1]);
            case "event" -> addEvent(parts[1]);
            default -> throw new InvalidCommandException(action);
        }
    }

    private void printTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("No tienes tareas todavía.");
            return;
        }
        System.out.println("Estas son tus tareas:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i - 1));
        }
    }

    private void addTodo(String input) throws EmptyDescriptionException {
        if (input.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task task = new Todo(input[1].trim());
        taskList.add(task);
        System.out.println("Agregado: " + task);
    }

    private void addDeadline(String input) throws ElchinoException {
        if (parts.length < 2 || !parts[1].contains("/by")) {
            throw new InvalidInputException();
        }
        String[] details = parts[1].split(" /by ", 2);
        if (details[0].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task task = new Deadline(details[0].trim(), details[1].trim());
        taskList.add(task);
        System.out.println("Agregado: " + task);
    }

    private void addEvent(String input) {
        String[] details = input.split(" /from | /to ");
        String description = details[0].trim();
        String start = details[1].trim();
        String end = details[2].trim();

        Task task = new Event(description, start, end);
        taskList.add(task);
        System.out.println("Agregado: " + task);
    }

    private int parseTaskNumber(String input) throws InvalidInputException {
        try {
            int n = Integer.parseInt(input);
            if (n < 1 || n > taskList.size()) {
                throw new InvalidInputException(n);
            }
            return n;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}