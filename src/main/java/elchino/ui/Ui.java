package elchino.ui;
import java.util.Scanner;

/* heavily inspired by addressbook-level2 TextUi.java */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hola! Yo me llamo El Chino. Encantado.");
        System.out.println("¿Le puedo ayudar en algo?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Gracias por usar El Chino. ¡Adiós!");
        showLine();
    }

    public void showLine() {
        System.out.println("--------------------");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}