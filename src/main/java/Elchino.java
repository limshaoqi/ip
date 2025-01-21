import java.util.Scanner;

public class Elchino {
    private static final String name = "El Chino";

    public static void main(String[] args) {
        System.out.println("Hola! Yo me llamo " + name + ". Encantado.");
        System.out.println("¿Le puedo ayudar en algo?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String token = scanner.nextLine();
            if (token.equals("bye")) {
                System.out.println("Hasta pronto!");
                return;
            }
            System.out.println(token);
        }
    }
}

