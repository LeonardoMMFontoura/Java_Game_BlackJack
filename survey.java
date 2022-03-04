import java.util.Locale;
import java.util.Scanner;

public class survey {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);
        System.out.println("Whats your name?");
        String name = scan.nextLine();
        System.out.println("Hello " + name);
        System.out.println("Quanto dinheiro quer pagar no cafe?");
        double valor = scan.nextDouble();
        System.out.println("Seu nome e " + name + " voce pagou " + valor + " no cafe");

        scan.close();
    }
}

