package elchino.tasks;
import java.time.LocalDateTime;
public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDate(deadline);
    }

    public String getBy() {
        return deadline.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getBy());
    }

    @Override
    public String storeTask() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadline.format(INPUT_DATE_FORMAT));
    }
}