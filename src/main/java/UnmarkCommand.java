import Exceptions.ElchinoException;
import Exceptions.InvalidInputException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String input) throws InvalidInputException {
        this.index = Integer.parseInt(input) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());
    }
}