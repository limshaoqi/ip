public class InvalidCommandException extends ElchinoException {
    public InvalidCommandException(String command) {
        super("Comando inválido: " + command);
    }
}