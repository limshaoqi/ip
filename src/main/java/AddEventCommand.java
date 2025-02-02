import Exceptions.ElchinoException;
import Exceptions.InvalidInputException;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

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
        tasks.addTask(new Event(description, from, to));
        storage.saveTasks(tasks.getTasks());
    }
}