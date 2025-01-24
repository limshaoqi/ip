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

//        while (true) {
//            String token = scanner.nextLine();
//            if (token.equals("bye")) {
//                System.out.println("Hasta pronto!");
//                return;
//            }
//
//            if (token.equals("list")) {
//                System.out.println("Estas son tus tareas:");
//                for (int i = 1; i <= list.size(); i++) {
//                    System.out.println(i + ". " + list.get(i - 1));
//                }
//            } else if (token.startsWith("mark")) {
//                String[] parts = token.split(" ");
//                int n = Integer.parseInt(parts[1]);
//                list.get(n - 1).setDone();
//                System.out.println("Ok, lo he marcado como hecho:");
//                System.out.println(list.get(n - 1));
//            }
//            else if (token.startsWith("unmark")) {
//                String[] parts = token.split(" ");
//                int n = Integer.parseInt(parts[1]);
//                list.get(n - 1).setNotDone();
//                System.out.println("Ok, lo he marcado como deshacer:");
//                System.out.println(list.get(n - 1));
//            } else {
//                Task task = new Task(token);
//                list.add(task);
//
//                System.out.println("agregado: " + token);
//            }

