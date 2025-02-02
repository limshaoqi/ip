package elchino.commands;
import elchino.exceptions.*;
import elchino.storage.Storage;
import elchino.tasks.*;
import elchino.ui.Ui;

/**
 * Command to add a new todo task
 */
public class AddTodoCommand extends Command {
    private final String description;
    public static final String MESSAGE_ADD_TODO = "Agregado: %s";

    /**
     * Creates a new AddTodoCommand with the given description
     * @param input Description of the todo task
     */
    public AddTodoCommand(String input) {
        this.description = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.printMessage(String.format(MESSAGE_ADD_TODO, task));
        storage.saveTasks(tasks.getTasks());
    }
}