import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Koliko števil želite vnesti? ");
        int uporabnikVnosSt = sc.nextInt();
        int stevila[] = new int[uporabnikVnosSt];
        for (int i = 0; i < stevila.length; i++) {
            System.out.printf("Vnesi %d. stevilo: ", i + 1);
            stevila[i] = sc.nextInt();
        }
        for (int clan : stevila) {
            System.out.println(clan);
        }

        sc.close();
    }
}