import java.util.Locale;
import java.util.Scanner;

public class delimiters {
        public static void main (String[] args){
            Scanner scan = new Scanner(System.in).useLocale(Locale.ENGLISH);
            System.out.println("Entre com dois inteiros e escreva na mesma linha");
            int num1 = scan.nextInt();
            int num2 = scan.nextInt();

            System.out.println("\nEntre com dois inteiros grandes na mesma linha");
            long long1 = scan.nextLong();
            long long2 = scan.nextLong();

            System.out.println("\nEntre com dois doubles grandes na mesma linha");
            double double1 = scan.nextDouble();
            double double2 = scan.nextDouble();

            System.out.println("\nEntre com dois textos na mesma linha");
            String texto1 = scan.next();
            String texto2 = scan.next();


            scan.close();
            System.out.println("\tOs numeros sao " + num1 + " " + num2);
            System.out.println("\tOs numeros sao " + long1 + " " + long2);
            System.out.println("\tOs numeros sao " + double1 + " " + double2);
            System.out.println("\tOs textos sao " + texto1 + " " + texto2);
        }
}
