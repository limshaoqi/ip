import Exceptions.ElchinoException;
import Exceptions.InvalidInputException;

public class UnmarkCommand extends Command {
    private final int index;
    public static final String MESSAGE_UNMARK_TASK = "Ok, lo he marcado como no hecho:\n%s";

    public UnmarkCommand(String input) throws InvalidInputException {
        this.index = Integer.parseInt(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        tasks.unmarkTask(index);
        ui.printMessage(String.format(MESSAGE_UNMARK_TASK, tasks.getTask(index)));
        storage.saveTasks(tasks.getTasks());
    }
}