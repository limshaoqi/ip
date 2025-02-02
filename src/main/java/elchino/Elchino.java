package elchino;

import java.util.ArrayList;
import elchino.exceptions.*;
import elchino.ui.Ui;
import elchino.tasks.*;
import elchino.storage.Storage;
import elchino.parser.Parser;
import elchino.commands.*;

/**
 * Represents the El Chino chatbot.
 * Initializes the chatbot and runs the chatbot.
 */
public class Elchino {
    private static final String name = "El Chino";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /* inspired by chatgpt4 for having tempTasks to fix assignment bug */
    /**
     * Constructor for El Chino with a specified file path.
     * @param filePath The file path to store the tasks.
     * @throws ElchinoException If there is an error initializing El Chino.
     */
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

    /**
     * Runs the El Chino chatbot, handling user input and executing commands.
     */
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

    /**
     * Main method to run the El Chino chatbot.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            new Elchino("data/tasks.txt").run();
        } catch (ElchinoException e) {
            System.out.println(e.getMessage());
        }
    }
}

