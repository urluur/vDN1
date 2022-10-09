import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Koliko stevil zelite vnesti? ");
        int uporabnikVnosSt = sc.nextInt();
        int[] stevila = new int[uporabnikVnosSt];
        for (int i = 0; i < stevila.length; i++) {
            System.out.printf("Vnesi %d. stevilo: ", i + 1);
            stevila[i] = sc.nextInt();
        }
        sc.close();


        // 1. število elementov (števil)
        System.out.printf("Stevilo elementov: %d\n", stevila.length);

        // 2. število različnih 
        System.out.printf("Stevilo razlicnih elementov: %d\n", countRazlicnaSt(stevila));
    
    }

    public static int countRazlicnaSt(int[] stevila) {
        if (stevila.length == 0) {
            return 0;
        }
        int[] stevilaDistinct = new int[stevila.length]; // bolj optimalna opcija bi bila z listi
        int count = 0;
        
        return count;
    }
}