import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Exceptions.ElchinoException;

public class Storage {
    private final String FILE_PATH;
        /* Create and Write to files inspired by:
        https://www.w3schools.com/java/java_files_create.asp
         */
    public Storage(String filePath) throws ElchinoException {
        this.FILE_PATH = filePath;
        if (!new File(FILE_PATH).exists()) {
            try {
                File file = new File(FILE_PATH);
                file.createNewFile();
            } catch (IOException e) {
                throw new ElchinoException("Error al crear el archivo.");
            }
        }
    }

    public ArrayList<Task> loadTasks() throws ElchinoException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                tasks.add(Storage.parseTask(task));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new ElchinoException("Error al leer el archivo.");
        }
        return tasks;
    }

    public void writeToMem(ArrayList<Task> task) throws ElchinoException {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task t : task) {
                writer.write(formatTask(t) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new ElchinoException("Error al escribir en el archivo.");
        }
    }

    private String formatTask(Task task) {
        String type = (task instanceof Todo) ? "T" : (task instanceof Deadline) ? "D" : "E";
        boolean status = task.isDone;
        String description = task.getDescription();
        
        if (task instanceof Todo) {
            return String.format("%s | %d | %s", type, status ? 1 : 0, description);
        } else if (task instanceof Deadline) {
            String by = ((Deadline) task).getBy();
            return String.format("%s | %d | %s | %s", type, status ? 1 : 0, description, by);
        } else {
            String from = ((Event) task).getFrom();
            String to = ((Event) task).getBy();
            return String.format("%s | %d | %s | %s | %s", type, status ? 1 : 0, description, from, to);
        }
    }
    
    private static Task parseTask(String task) {
        String[] details = task.split(" \\| ");
        String type = details[0];
        boolean status = details[1].equals("1");
        String description = details[2];
        
        if (type.equals("T")) {
            return new Todo(description);
        } else if (type.equals("D")) {
            String by = details[3];
            return new Deadline(description, by);
        } else {
            String from = details[3];
            String to = details[4];
            return new Event(description, from, to);
        }
    }
}

