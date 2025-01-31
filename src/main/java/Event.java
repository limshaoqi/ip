public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getFrom() {
        return start;
    }

    public String getBy() {
        return end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }

    @Override
    public String storeTask() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, start, end);
    }
}