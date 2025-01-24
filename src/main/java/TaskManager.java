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
            case "mark" -> markTask(parts[1]);
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

    private void markTask(String input) throws ElchinoException {
        int taskNumber = parseTaskNumber(input);
        taskList.get(taskNumber - 1).setDone();
        System.out.println("Ok, lo he marcado como hecho:");
        System.out.println(taskList.get(taskNumber - 1));
    }

    private void addTodo(String input) throws EmptyDescriptionException {
        if (input.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task task = new Todo(input.trim());
        taskList.add(task);
        System.out.println("Agregado: " + task);
    }

    private void addDeadline(String input) throws ElchinoException {
        if (!input.contains("/by")) {
            throw new InvalidInputException();
        }
        String[] details = input.split(" /by ", 2);
        if (details[0].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }

        Task task = new Deadline(details[0].trim(), details[1].trim());
        taskList.add(task);
        System.out.println("Agregado: " + task);
    }

    private void addEvent(String input) throws ElchinoException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new InvalidInputException();
        }
        String[] details = input.split(" /from | /to ", 3);
        if (details.length < 3 || details[0].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task task = new Event(details[0].trim(), details[1].trim(), details[2].trim());
        taskList.add(task);
        System.out.println("Agregado: " + task);
    }

    private int parseTaskNumber(String input) throws InvalidInputException {
        try {
            int taskNumber = Integer.parseInt(input);
            if (taskNumber < 1 || taskNumber > taskList.size()) {
                throw new InvalidInputException(taskNumber);
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}