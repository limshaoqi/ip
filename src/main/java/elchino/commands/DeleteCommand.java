package elchino.commands;
import elchino.exceptions.ElchinoException;
import elchino.exceptions.InvalidInputException;
import elchino.storage.Storage;
import elchino.tasks.Deadline;
import elchino.tasks.Task;
import elchino.tasks.TaskList;
import elchino.ui.Ui;


public class DeleteCommand extends Command {
    private final int index;
    public static final String MESSAGE_DELETE = "Eliminado: %s";

    public DeleteCommand(String input) throws InvalidInputException {
        this.index = Integer.parseInt(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        Task removedTask = tasks.removeTask(index);
        ui.printMessage(String.format(MESSAGE_DELETE, removedTask));
        storage.saveTasks(tasks.getTasks());
    }
}