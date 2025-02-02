package elchino.commands;
import elchino.exceptions.ElchinoException;
import elchino.exceptions.InvalidInputException;
import elchino.storage.Storage;
import elchino.tasks.Deadline;
import elchino.tasks.Task;
import elchino.tasks.TaskList;
import elchino.ui.Ui;


public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;
    public static final String MESSAGE_ADD_DEADLINE = "Agregado: %s";

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
        Task task = new Deadline(description, deadline);
        tasks.addTask(task);
        ui.printMessage(String.format(MESSAGE_ADD_DEADLINE, task));
        storage.saveTasks(tasks.getTasks());
    }
}