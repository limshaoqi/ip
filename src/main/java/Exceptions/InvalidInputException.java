package Exceptions;

public class InvalidInputException extends ElchinoException {
    public InvalidInputException() {
        super("Entrada inválida: Por favor ingresa un número válido.");
    }

    public InvalidInputException(int taskNumber) {
        super("Lo siento, no puedo encontrar la tarea " + taskNumber + ".");
    }
}
