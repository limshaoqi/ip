package elchino.tasks;

import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /* Inspired by chatGPT to use DateTimeFormatter constants */
    protected static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    protected static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha inválida. Formato esperado: dd/MM/yyyy HHmm");
        }
    }

    public abstract String getTaskType();

    public abstract String storeTask();

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
