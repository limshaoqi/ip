package elchino.commands;
import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Command to exit the program
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}