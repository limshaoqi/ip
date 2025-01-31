import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = parseDate(start);
        this.end = parseDate(end);
    }

    public String getFrom() {
        return start.format(OUTPUT_DATE_FORMAT);
    }

    public String getBy() {
        return end.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (from: %s to: %s)", super.toString(), description, getFrom(), getBy());
    }

    @Override
    public String storeTask() {
        return String.format("E | %d | %s | %s | %s",
                             isDone ? 1 : 0, description,
                             start.format(INPUT_DATE_FORMAT),
                             end.format(INPUT_DATE_FORMAT));
    }
}