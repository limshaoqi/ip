import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Elchino {
    private static final String name = "El Chino";
    private static final List<String> list = new ArrayList<>();

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

            if (token.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                list.add(token);
                System.out.println("agregado: " + token);
            }
        }
    }
}

