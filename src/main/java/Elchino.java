import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Elchino {
    private static final String name = "El Chino";
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        System.out.println("Hola! Yo me llamo " + name + ". Encantado.");
        System.out.println("¿Le puedo ayudar en algo?");

        Scanner scanner = new Scanner(System.in);
        processCommands(scanner);
        scanner.close();
        System.out.println("Gracias por usar El Chino. ¡Adiós!");
    }

    private static void processCommands(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                System.out.println("Hasta pronto!");
                break;
            }
            taskManager.handleCommand(input);
        }
    }
}

