package elchino.commands;
import elchino.exceptions.ElchinoException;
import elchino.exceptions.InvalidInputException;
import elchino.storage.Storage;
import elchino.tasks.Deadline;
import elchino.tasks.Task;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;
    public static final String MESSAGE_ADD_DEADLINE = "Agregado: %s";

    /**
     * Constructor for AddDeadlineCommand with a description and deadline.
     * @param input String containing the description and deadline.
     * @throws InvalidInputException if input format is invalid.
     */
    public AddDeadlineCommand(String input) throws InvalidInputException {
        if (!input.contains("/by")) {
            throw new InvalidInputException("Por favor usa /by para especificar la fecha.");
        }
        String[] parts = input.split(" /by ", 2);
        this.description = parts[0].trim();
        this.deadline = parts[1].trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        Task task = new Deadline(description, deadline);
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        return String.format(MESSAGE_ADD_DEADLINE, task);
    }
}