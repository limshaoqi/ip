package elchino.storage;

import java.io.*;

import java.nio.file.Files;

import java.nio.file.Path;

import java.util.ArrayList;

import elchino.exceptions.*;

import elchino.tasks.*;

/**
 * Class to handle storage of tasks, allowing tasks to be loaded from and saved to a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructor for Storage.
     * @param filePath The path to the file to store tasks.
     * @throws ElchinoException if an error occurs during initialization
     */
    public Storage(String filePath) throws ElchinoException {
        this.filePath = Path.of(filePath);

        try {
            if (!Files.exists(this.filePath.getParent())) {
                Files.createDirectories(this.filePath.getParent());
            }
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            throw new ElchinoException("Error al crear el archivo de tareas.");
        }
    }

    /**
     * Loads tasks from the file.
     * @return An ArrayList of tasks loaded from the file.
     * @throws ElchinoException if an error occurs during loading
     */
    public ArrayList<Task> loadTasks() throws ElchinoException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Error al cargar una tarea inválida: " + line);
                }
            }
        } catch (IOException e) {
            throw new ElchinoException("Error al leer el archivo.");
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     * @param tasks An ArrayList of tasks to be saved to the file.
     * @throws ElchinoException if an error occurs during saving.
     */
    public void saveTasks(ArrayList<Task> tasks) throws ElchinoException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.storeTask());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ElchinoException("Error al escribir en el archivo.");
        }
    }


    /**
     * Parses a line from the file into a Task object.
     * @param line The line to be parsed as a task.
     * @return The Task object parsed from the line.
     */
    private Task parseTask(String line) {
        String[] details = line.split(" \\| ");
        if (details.length < 3) {
            System.out.println("⚠️ Error: Línea malformada en el archivo: " + line);
            return null;
        }

        String type = details[0];
        boolean isDone = details[1].equals("1");
        String description = details[2];

        try {
            switch (type) {
                case "T":
                    Task todo = new Todo(description);
                    if (isDone) todo.setDone();
                    return todo;
                case "D":
                    if (details.length < 4) {
                        System.out.println("Error: Línea malformada en el archivo: " + line);
                        return null;
                    }
                    Task deadline = new Deadline(description, details[3]);
                    if (isDone) deadline.setDone();
                    return deadline;
                case "E":
                    if (details.length < 5) {
                        System.out.println("Error: Línea malformada en el archivo: " + line);
                        return null;
                    }
                    Task event = new Event(description, details[3], details[4]);
                    if (isDone) event.setDone();
                    return event;
                default:
                    System.out.println("Error: Tipo de tarea desconocido en el archivo: " + line);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error: No se pudo procesar la tarea en el archivo: " + line);
            return null;
        }
    }
}
