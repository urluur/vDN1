import java.util.Arrays;
import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int uporabnikVnosSt;
        do {
            System.out.print("Koliko stevil zelite vnesti? ");
            uporabnikVnosSt = sc.nextInt();
        } while (uporabnikVnosSt < 1);
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
        vrniDrugoNajmanjso(stevila);

        // 9. povprecje vseh stevil
        System.out.println("Povprečje vseh stevil: " + vrniPovprecje(stevila));

        // 10. standardna deviacija
        System.out.println("Standardna deviacija: " + stdDeviacija(stevila));

        // 11. mediana
        System.out.println("Mediana: " + vrniMediano(stevila));

        // 12. vsota vseh števil
        System.out.println("Vsota vseh stevil: " + vrniVsoto(stevila));

        // 13. koliko je palindromnih števil
        System.out.println("Palindromnih stevil je: " + vrniStPalindromnih(stevila));

        // 14. najvecji mozni palindrom manjsi od najvecjega stevila
        maxMozenPalindrom(stevila);

        // 15. obraten formatiran izpis
        obratenIzpis(stevila);
    }

    public static int countRazlicnaSt(int[] stevila) {
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

    public static void vrniDrugoNajmanjso(int[] stevila) {
        if (stevila.length < 2) {
            System.out.println("Drugo najmanjse stevilo ne obstaja");
            return;
        }
        Arrays.sort(stevila);
        int dolzina_distinct_arr = vrniDistinctArray(stevila).length;
        int i = 0;
        while (stevila[i] == stevila[0]) {
            i++;
            if(i > dolzina_distinct_arr) {
                System.out.println("Drugo najmanjse stevilo ne obstaja");
                return;
            }
        }
        System.out.println("Drugo najmanjse stevilo je " + stevila[i]);
    }

    public static double vrniPovprecje(int[] stevila) {
        double skupno = 0;

        for (int stevilo : stevila) {
            skupno += stevilo;
        }

        return skupno / stevila.length;
    }

    public static double vrniVarianco(int[] stevila) {
        double sestevek = 0;
        double povprecje = vrniPovprecje(stevila);
        for (int stevilo : stevila) {
            sestevek += Math.pow((stevilo - povprecje), 2);
        }
        return sestevek / stevila.length;
    }

    public static double stdDeviacija(int[] stevila) {
        return Math.sqrt(vrniVarianco(stevila));
    }

    public static double vrniMediano(int[] stevila) {
        double mediana;
        if (stevila.length % 2 == 0) {
            int indeks_spodnjega = (stevila.length / 2) - 1; // -1 zato ker se array zacne z 0
            int indeks_zgornjega = indeks_spodnjega + 1;

            mediana = (stevila[indeks_spodnjega] + stevila[indeks_zgornjega]) / 2.0;
        }
        else {
            int indeks_srednjega = (int) Math.ceil(stevila.length / 2.0);
            mediana = stevila[indeks_srednjega];
        }
        return mediana;
    }

    public static int vrniVsoto(int[] stevila) {
        int vsota = 0;
        for (int stevilo : stevila) {
            vsota += stevilo;
        }
        return vsota;
    }

    public static boolean jePalindrom(int stevilo) {
        String original = Integer.toString(Math.abs(stevilo));
        String reversed = new StringBuilder(original).reverse().toString();
        return (original.equals(reversed)) ? true : false;
    }

    public static int vrniStPalindromnih(int[] stevila) {
        int stevec = 0;

        for (int stevilo : stevila) {
            if (jePalindrom(stevilo)) {
                stevec++;
            }
        }

        return stevec;
    }

    public static void maxMozenPalindrom(int[] stevila) {
        int najvecje = vrniNajvecje(stevila);
        int int_overflow_safe_stevec = 0;
        int palindrom = 0;
        for (int i = najvecje - 1; i >= Integer.MIN_VALUE; i--) {
            if(jePalindrom(i)) {
                palindrom = i;
                int_overflow_safe_stevec++;
                break;
            }
        }

        if (int_overflow_safe_stevec == 0) {
            System.out.println("Najvecji mozni palindom manjsi od najvecjega stevila je manjsi od dovoljenega stevilskega obmocja");
        }
        else {
            System.out.println("Najvecji mozni palindom manjsi od najvecjega stevila: " + palindrom);
        }
    }

    public static void obratenIzpis(int[] stevila) {
        System.out.println("Vsa stevila v obratnem vrstnem redu:");
        for (int i = stevila.length - 1; i > 0; i--) {
            System.out.print(stevila[i] + ", ");
        }
        System.out.print(stevila[0] + "\n");
    }
}