import java.util.ArrayList;
import java.util.List;
public class TaskManager {
    private final List<Task> taskList = new ArrayList<>();

    public void handleCommand(String command) {
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
            case "todo" -> {
                String description = parts[1].trim();
                Task task = new Todo(description);
                taskList.add(task);
                System.out.println("Agregado: " + task);
            }
            case "deadline" -> addDeadline(parts[1]);
            case "event" -> addEvent(parts[1]);
            default -> System.out.println("Lo siento, no entiendo.");
        }
    }

    private void printTaskList() {
        System.out.println("Estas son tus tareas:");
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println(i + ". " + taskList.get(i - 1));
                }
    }

    private void addDeadline(String input) {
        String[] details = input.split(" /by ", 2);
        String description = details[0].trim();
        String deadline = details[1].trim();

        Task task = new Deadline(description, deadline);
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
}