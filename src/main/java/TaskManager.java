import java.util.ArrayList;
import java.util.List;

import Exceptions.ElchinoException;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidInputException;
import Exceptions.EmptyDescriptionException;

public class TaskManager {
    private final List<Task> taskList;
    private Storage storage;

    public TaskManager(String filepath) {
        this.taskList = new ArrayList<>();
        Storage tempStorage = null;
        try {
            tempStorage = new Storage(filepath);
            this.taskList.addAll(tempStorage.loadTasks());
        } catch (ElchinoException e) {
            System.out.println("Error al cargar las tareas.");
        }
        this.storage = tempStorage;
    }

    public void handleCommand(String command) throws ElchinoException {
        String[] parts = command.split(" ", 2);
        String action = parts[0];
        String arguments = (parts.length > 1) ? parts[1] : "";
        switch (action) {
            case "list" -> printTaskList();
            case "mark" -> markTask(arguments);
            case "unmark" -> unmarkTask(arguments);
            case "todo" -> addTodo(arguments);
            case "deadline" -> addDeadline(arguments);
            case "event" -> addEvent(arguments);
            case "delete" -> deleteTask(arguments);
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
        storage.saveTasks(new ArrayList<>(taskList));
    }

    private void unmarkTask(String input) throws ElchinoException {
        int taskNumber = parseTaskNumber(input);
        taskList.get(taskNumber - 1).setNotDone();
        System.out.println("Ok, lo he marcado como deshacer:");
        System.out.println(taskList.get(taskNumber - 1));
        storage.saveTasks(new ArrayList<>(taskList));
    }

    private void addTodo(String input) throws EmptyDescriptionException, ElchinoException {
        if (input.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task task = new Todo(input.trim());
        taskList.add(task);
        System.out.println("Agregado: " + task);
        storage.saveTasks(new ArrayList<>(taskList));
    }

    private void addDeadline(String input) throws ElchinoException {
        if (input.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (!input.contains("/by")) {
            throw new InvalidInputException("Por favor usa /by para especificar la fecha y hora.");
        }
        String[] details = input.split(" /by ", 2);
        if (details[0].trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }

        Task task = new Deadline(details[0].trim(), details[1].trim());
        taskList.add(task);
        System.out.println("Agregado: " + task);
        storage.saveTasks(new ArrayList<>(taskList));
    }

    private void addEvent(String input) throws ElchinoException {
        if (input.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new InvalidInputException("Por favor usa /from y /to para especificar la fecha y hora.");
        }
        String[] parts = input.split(" /from ", 2);
        if (parts.length < 2) throw new InvalidInputException("Formato incorrecto para Event.");

        String[] details = parts[1].split(" /to ", 2);
        if (details.length < 2) throw new InvalidInputException("Formato incorrecto para Event.");

        Task task = new Event(parts[0].trim(), details[0].trim(), details[1].trim());
        taskList.add(task);
        System.out.println("Agregado: " + task);
        storage.saveTasks(new ArrayList<>(taskList));
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

    private void deleteTask(String input) throws ElchinoException {
        if (input.trim().isEmpty()) {
            throw new InvalidInputException();
        }

        int taskNumber = parseTaskNumber(input);
        Task task = taskList.remove(taskNumber - 1);
        System.out.println("Ok, he eliminado esta tarea:");
        System.out.println(task);
        System.out.println("Ahora tienes " + taskList.size() + " tareas en tu lista.");
        storage.saveTasks(new ArrayList<>(taskList));
    }
}
