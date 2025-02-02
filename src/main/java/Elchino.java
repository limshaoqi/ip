import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exceptions.ElchinoException;

public class Elchino {
    private static final String name = "El Chino";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /* inspired by chatgpt4 for having tempTasks to fix assignment bug */
    public Elchino(String filePath) throws ElchinoException {
        this.ui = new Ui();
        TaskList tempTasks;
        Storage tempStorage;

        try {
            tempStorage = new Storage(filePath);
            tempTasks = new TaskList(tempStorage.loadTasks());
        } catch (ElchinoException e) {
            ui.printMessage(e.getMessage());
            tempStorage = new Storage(filePath);
            tempTasks = new TaskList(new ArrayList<>());
        }
        this.storage = tempStorage;
        this.tasks = tempTasks;
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String input = ui.readCommand();

                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (ElchinoException e) {
                ui.printMessage(e.getMessage());
            } catch (Exception e) {
                ui.printMessage("¡Error desconocido! Por favor, inténtalo de nuevo.");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Elchino("data/tasks.txt").run();
        } catch (ElchinoException e) {
            System.out.println(e.getMessage());
        }
    }
}

