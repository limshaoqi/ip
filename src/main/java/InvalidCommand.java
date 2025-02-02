public class InvalidCommand extends Command {
    private final String command;
    public static final String MESSAGE_INVALID_COMMAND = "Lo siento, no entiendo el comando: %s";

    public InvalidCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(String.format(MESSAGE_INVALID_COMMAND, command));
    }
}