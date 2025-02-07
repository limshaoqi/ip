package elchino.commands;
import elchino.exceptions.*;
import elchino.storage.Storage;
import elchino.tasks.*;
import elchino.ui.Ui;

/**
 * Command to add a new event task
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;
    public static final String MESSAGE_ADD_EVENT = "Agregado: %s";

    /**
     * Creates a new AddEventCommand with the given description, start and end dates
     * @param input Description of the event task, start and end dates
     * @throws InvalidInputException if the input does not contain /from or /to
     */
    public AddEventCommand(String input) throws InvalidInputException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new InvalidInputException("Por favor usa /from y /to para especificar la fecha.");
        }
        String[] parts = input.split(" /from | /to ", 3);
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        return String.format(MESSAGE_ADD_EVENT, task);
    }
}