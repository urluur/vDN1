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

        // 6. število, ki se največkrat ponovi
        steviloKiSeNajveckratPonovi(stevila);

        // 7. največje število
        System.out.println("Najvecje stevilo je: " + vrniNajvecje(stevila));

        // 8. drugo najmanjšo vrednost števil
        System.out.println("Druga najmanjsa vrednost stevil je: " + vrniDrugoNajmanjso(stevila));
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
        double st_razlicnih = stevila.length;

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

    public static void steviloKiSeNajveckratPonovi(int[] stevila) {
        int[] stevilaDistinct = vrniDistinctArray(stevila);
        int[] stevecDistinct = new int[stevilaDistinct.length];

        for (int stevilo : stevila) {
            for (int i = 0; i < stevilaDistinct.length; i++) {
                if (stevilo == stevilaDistinct[i]) {
                    stevecDistinct[i]++;
                }
            }
        }
        int lokacija_najvecjega = 0; 
        int ponavljanje_enakofrekvencnih = 0;
        int najvecja_ponovitev = 0;

        for (int i = 0; i < stevecDistinct.length; i++) {
            if (stevecDistinct[i] > najvecja_ponovitev) {
                najvecja_ponovitev = stevecDistinct[i];
                lokacija_najvecjega = i;
                ponavljanje_enakofrekvencnih = 1;
            }
            else if (stevecDistinct[i] == najvecja_ponovitev) {
                ponavljanje_enakofrekvencnih++;
            }
        }

        System.out.println("Najveckrat se ponovi stevilo " + stevilaDistinct[lokacija_najvecjega]);
        lokacija_najvecjega++;
        while(ponavljanje_enakofrekvencnih > 1) {
            if (stevecDistinct[lokacija_najvecjega] == najvecja_ponovitev) {
                System.out.println("ampak si deli prvo stopničko s stevilom " + stevilaDistinct[lokacija_najvecjega]);
                ponavljanje_enakofrekvencnih--;
            }
            lokacija_najvecjega++;
        }
    }

    public static int vrniNajvecje(int[] stevila) {
        int najvecje = Integer.MIN_VALUE;
        for (int stevilo : stevila) {
            if (stevilo > najvecje) {
                najvecje = stevilo;
            }
        }
        return najvecje;
    }

    public static int vrniDrugoNajmanjso(int[] stevila) {
        Arrays.sort(stevila);
        int i = 0;
        while (stevila[i] == stevila[0]) {
            i++;
            if(i > stevila.length) {
                return -1;
            }
        }
        return stevila[i];
    }
}