import Exceptions.ElchinoException;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String input) {
        this.description = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        tasks.addTask(new Todo(description));
        storage.saveTasks(tasks.getTasks());
    }
}