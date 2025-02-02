import Exceptions.ElchinoException;
import Exceptions.InvalidInputException;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    public AddDeadlineCommand(String input) throws InvalidInputException {
        if (!input.contains("/by")) {
            throw new InvalidInputException("Por favor usa /by para especificar la fecha.");
        }
        String[] parts = input.split(" /by ", 2);
        this.description = parts[0].trim();
        this.deadline = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        tasks.addTask(new Deadline(description, deadline));
        storage.saveTasks(tasks.getTasks());
    }
}