import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("informe um valor");
        Scanner reader = new Scanner(System.in);
        String command = reader.next();
        reader.close();

        System.out.println(command);
    }
}
