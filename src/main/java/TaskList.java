import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index - 1);
    }

    public void markTask(int index) {
        tasks.get(index - 1).setDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index - 1).setNotDone();
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas.");
        } else {
            System.out.println("Estas son tus tareas:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}