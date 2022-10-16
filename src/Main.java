import java.util.Arrays;
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
        
        // 3. 4.  število sodih in lihih števil
        steviloSodihInLihih(stevila);

        // 5. frekvenca pojavitev vsakega števila (v procentih)
        frekvencaPojavitveProcenti(stevila);
        
    }

    public static int countRazlicnaSt(int[] stevila) {
        if (stevila.length == 0) {
            return 0;
        }
        int[] stevilaDistinct = vrniDistinctArray(stevila);

        return stevilaDistinct.length;

    }

    public static int[] vrniDistinctArray(int[] stevila) {
        int[] stevilaDistinctUncut = new int[stevila.length];

        int count = 0;
        
        for (int i = 0; i < stevila.length; i++) {
            boolean razlicno = true;
            if (i == 0) {
                stevilaDistinctUncut[i] = stevila[i];
                count++;
                continue;
            }
            for (int j = 0; j <= count; j++) {
                if (stevila[i] == stevilaDistinctUncut[j]) {
                    razlicno = false;
                    break;
                }
            }
            if (razlicno) {
                stevilaDistinctUncut[count] = stevila[i];
                count++;
            }
        }

        int[] stevilaDistinctCut = new int[count];
        System.arraycopy(stevilaDistinctUncut, 0, stevilaDistinctCut, 0, count);
        return stevilaDistinctCut;
    }

    public static void steviloSodihInLihih(int[] stevila) {
        int st_sodih = 0;
        int st_lihih = 0;

        for (int stevilo : stevila) {
            if (stevilo % 2 == 0) {
                st_sodih++;
            }
            else {
                st_lihih++;
            }
        }
        System.out.printf("Stevilo sodih stevil: %d\n", st_sodih);
        System.out.printf("Stevilo lihih stevil: %d\n", st_lihih);
    }

    public static void frekvencaPojavitveProcenti(int[] stevila) {
        int[] stevilaDistinct = vrniDistinctArray(stevila);
        System.out.println(Arrays.toString(stevilaDistinct));
        double st_razlicnih = stevilaDistinct.length;

        for (int stevilo : stevilaDistinct) {
            int st_ponovitev = 0;
            for (int trenutno_st : stevila) {
                if (stevilo == trenutno_st) {
                    st_ponovitev++;
                }
            }
            double odstotki = (st_ponovitev / st_razlicnih) * 100;
            System.out.printf("Stevilo %d predstavlja %f%% vseh stevil.\n", stevilo, odstotki);
        }
    }


}