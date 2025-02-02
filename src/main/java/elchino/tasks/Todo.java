package elchino.tasks;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storeTask() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

}