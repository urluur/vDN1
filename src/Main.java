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
        int[] stevilaDistinct = new int[stevila.length];

        int count = 0;
        
        for (int i = 0; i < stevila.length; i++) {
            boolean razlicno = false;
            if (i == 0) {
                stevilaDistinct[i] = stevila[i];
                count++;
                continue;
            }
            for (int j = 0; j <= count; j++) {
                if (stevila[i] == stevilaDistinct[j]) {
                    break;
                }
                else {
                    razlicno = true;
                }
            }
            if (razlicno) {
                stevilaDistinct[count] = stevila[i];
                count++;
            }
        }

        return count;
    }
}