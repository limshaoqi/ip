import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exceptions.ElchinoException;

public class Elchino {
    private static final String name = "El Chino";
    private static final TaskManager taskManager = new TaskManager("./data/tasks.txt");

    public static void main(String[] args) {
        System.out.println("Hola! Yo me llamo " + name + ". Encantado.");
        System.out.println("¿Le puedo ayudar en algo?");

        Scanner scanner = new Scanner(System.in);
        try {
            processCommands(scanner);
        } finally {
            scanner.close();
            System.out.println("Gracias por usar El Chino. ¡Adiós!");
        }
    }

    private static void processCommands(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.equals("bye")) {
                    System.out.println("Hasta pronto!");
                    break;
                }
                taskManager.handleCommand(input);
            } catch (ElchinoException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Lo siento, ha ocurrido un error inesperado.");
            } finally {
                System.out.println("--------------------");
            }
        }
    }
}

