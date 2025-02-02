package elchino.exceptions;
import elchino.exceptions.ElchinoException;

public class InvalidCommandException extends ElchinoException {
    public InvalidCommandException(String command) {
        super("Comando inválido: " + command);
    }
}