import Exceptions.ElchinoException;
import Exceptions.InvalidInputException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String input) throws InvalidInputException {
        this.index = Integer.parseInt(input) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        tasks.removeTask(index);
        storage.saveTasks(tasks.getTasks());
    }
}