import java.util.Scanner;

public class survey {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Whats your name?");
        String name = scan.nextLine();
        System.out.println("Hello " + name);
    }
}

