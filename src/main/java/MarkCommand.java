import Exceptions.ElchinoException;
import Exceptions.InvalidInputException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) throws InvalidInputException {
        this.index = Integer.parseInt(input) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());
    }
}