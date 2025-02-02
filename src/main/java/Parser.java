import Exceptions.ElchinoException;

public class Parser {
    public static Command parse(String input) throws ElchinoException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch(command) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parts[1]);
            case "unmark":
                return new UnmarkCommand(parts[1]);
            case "todo":
                return new AddTodoCommand(parts[1]);
            case "deadline":
                return new AddDeadlineCommand(parts[1]);
            case "event":
                return new AddEventCommand(parts[1]);
            case "delete":
                return new DeleteCommand(parts[1]);
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand(command);
        }
    }
}