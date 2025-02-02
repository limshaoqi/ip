package elchino.commands;

import elchino.exceptions.ElchinoException;
import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ElchinoException {
        ArrayList<String> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            ui.printMessage("No se encontraron tareas coincidentes.");
        } else {
            ui.printMessage("Aquí están los partidos:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.printMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
