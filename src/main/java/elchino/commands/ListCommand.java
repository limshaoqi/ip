package elchino.commands;
import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();
    }
}