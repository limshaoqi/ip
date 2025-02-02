package elchino.exceptions;

import elchino.exceptions.ElchinoException;

public class EmptyDescriptionException extends ElchinoException {
    public EmptyDescriptionException() {
        super("Descripción vacía: Por favor ingresa una descripción válida.");
    }
}