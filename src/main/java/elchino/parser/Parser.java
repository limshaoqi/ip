package elchino.parser;
import elchino.exceptions.*;
import elchino.commands.*;

public class Parser {
    public static Command parse(String input) throws ElchinoException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        return switch (command) {
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(parts[1]);
            case "unmark" -> new UnmarkCommand(parts[1]);
            case "todo" -> new AddTodoCommand(parts[1]);
            case "deadline" -> new AddDeadlineCommand(parts[1]);
            case "event" -> new AddEventCommand(parts[1]);
            case "delete" -> new DeleteCommand(parts[1]);
            case "bye" -> new ExitCommand();
            default -> new InvalidCommand(command);
        };
    }
}