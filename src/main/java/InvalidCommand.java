public class InvalidCommand extends Command {
    private final String command;

    public InvalidCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Lo siento, no entiendo el comando: " + command);
    }
}