package elchino.commands;
import elchino.exceptions.*;
import elchino.storage.Storage;
import elchino.tasks.*;
import elchino.ui.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;
    public static final String MESSAGE_ADD_EVENT = "Agregado: %s";

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.printMessage(String.format(MESSAGE_ADD_EVENT, task));
        storage.saveTasks(tasks.getTasks());
    }
}