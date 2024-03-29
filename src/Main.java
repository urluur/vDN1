import java.util.Arrays;
import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int uporabnik_vnos_st;
        do {
            System.out.print("Koliko stevil zelite vnesti? ");
            uporabnik_vnos_st = sc.nextInt();
        } while (uporabnik_vnos_st < 1);
        int[] stevila = new int[uporabnik_vnos_st];
        for (int i = 0; i < stevila.length; i++) {
            System.out.printf("Vnesi %d. stevilo: ", i + 1);
            stevila[i] = sc.nextInt();
        }
        sc.close();


        // 1. število elementov (števil)
        System.out.printf("\nStevilo elementov: %d\n", stevila.length);

        // 2. število različnih 
        System.out.printf("Stevilo razlicnih elementov: %d\n\n", countRazlicnaSt(stevila));
        
        // 3. 4.  število sodih in lihih števil
        steviloSodihInLihih(stevila);

        // 5. frekvenca pojavitev vsakega števila (v procentih)
        frekvencaPojavitveProcenti(stevila);

        // 6. število, ki se največkrat ponovi
        steviloKiSeNajveckratPonovi(stevila);

        // 7. največje število
        System.out.printf("Najvecje stevilo je: %d\n", vrniNajvecje(stevila));

        // 8. drugo najmanjšo vrednost števil
        vrniDrugoNajmanjso(stevila);

        // 9. povprecje vseh stevil
        System.out.printf("Povprecje vseh stevil: %f\n", vrniPovprecje(stevila));

        // 10. standardna deviacija
        System.out.printf("Standardna deviacija: %f\n", stdDeviacija(stevila));

        // 11. mediana
        System.out.printf("Mediana: %f\n\n", vrniMediano(stevila));

        // 12. vsota vseh števil
        System.out.printf("Vsota vseh stevil: %d\n\n", vrniVsoto(stevila));

        // 13. koliko je palindromnih števil
        System.out.printf("Palindromnih stevil je: %d\n", vrniStPalindromnih(stevila));

        // 14. najvecji mozni palindrom manjsi od najvecjega stevila
        maxMozenPalindrom(stevila);

        // 15. obraten formatiran izpis
        obratenIzpis(stevila);
    }

    /**
     * Vrne koliko razlicnih stevil je v tabeli
     * @param stevila Opazovana tabela
     * @return Stevilo razlicnih stevil
     */
    public static int countRazlicnaSt(int[] stevila) {
        int[] stevila_distinct = vrniDistinctArray(stevila);
        return stevila_distinct.length;
    }

    /**
     * Vrne tabelo stevil, ki se ne ponavljajo
     * @param stevila Opazovana tabela
     * @return Tabela brez ponavljajocih stevil
     */
    public static int[] vrniDistinctArray(int[] stevila) {
        int[] stevila_distinct_uncut = new int[stevila.length];

        int count = 0;
        
        for (int i = 0; i < stevila.length; i++) {
            boolean razlicno = true;
            if (i == 0) {
                stevila_distinct_uncut[i] = stevila[i];
                count++;
                continue;
            }
            for (int j = 0; j <= count; j++) {
                if (stevila[i] == stevila_distinct_uncut[j]) {
                    razlicno = false;
                    break;
                }
            }
            if (razlicno) {
                stevila_distinct_uncut[count] = stevila[i];
                count++;
            }
        }

        int[] stevila_distinct_cut = new int[count];
        System.arraycopy(stevila_distinct_uncut, 0, stevila_distinct_cut, 0, count);
        return stevila_distinct_cut;
    }

    /**
     * Izpise stevilo sodih in stevilo lihih stevil v tabeli
     * @param stevila Opazovana tabela
     */
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
        System.out.printf("Stevilo lihih stevil: %d\n\n", st_lihih);
    }

    /**
     * Izpise procentualno vrednost ponovitve vsakega stevila v tabeli
     * @param stevila Opazovana tabela
     */
    public static void frekvencaPojavitveProcenti(int[] stevila) {
        int[] stevila_distinct = vrniDistinctArray(stevila);
        double st_razlicnih = stevila.length;

        for (int stevilo : stevila_distinct) {
            int st_ponovitev = 0;
            for (int trenutno_st : stevila) {
                if (stevilo == trenutno_st) {
                    st_ponovitev++;
                }
            }
            double odstotki = (st_ponovitev / st_razlicnih) * 100;
            System.out.printf("Stevilo %d predstavlja %f%% vseh stevil.\n", stevilo, odstotki);
        }
        System.out.println();
    }

    /**
     * Izpise stevilo/a li se najveckrat ponovi/jo
     * @param stevila Opazovana tabela
     */
    public static void steviloKiSeNajveckratPonovi(int[] stevila) {
        int[] stevila_distinct = vrniDistinctArray(stevila);
        int[] stevec_distinct = new int[stevila_distinct.length];

        // preverimo za vsako og stevilo kolikokrat se ponovi
        for (int stevilo : stevila) {
            for (int i = 0; i < stevila_distinct.length; i++) {
                if (stevilo == stevila_distinct[i]) {
                    stevec_distinct[i]++;
                }
            }
        }
        // vazni sta tabeli stevila_distinct in stevec_distinct ker za vsako distinct stevilo
        // je na istolezecem mestu v tabeli stevec_distinct njegovo stevilo ponovitev

        int lokacija_najvecjega = 0; 
        int ponavljanje_enakofrekvencnih = 0;
        int najvecja_ponovitev = 0;

        // ugotovimo ali se razlicna stevila ponavljajo najveckrat
        for (int i = 0; i < stevec_distinct.length; i++) {
            if (stevec_distinct[i] > najvecja_ponovitev) {
                najvecja_ponovitev = stevec_distinct[i];
                lokacija_najvecjega = i;
                ponavljanje_enakofrekvencnih = 1;
            }
            else if (stevec_distinct[i] == najvecja_ponovitev) {
                ponavljanje_enakofrekvencnih++;
            }
        }

        System.out.println("Najveckrat se ponovi stevilo " + stevila_distinct[lokacija_najvecjega]);
        lokacija_najvecjega++;
        while(ponavljanje_enakofrekvencnih > 1) {
            if (stevec_distinct[lokacija_najvecjega] == najvecja_ponovitev) {
                System.out.println("ampak si deli prvo stopničko s stevilom " + stevila_distinct[lokacija_najvecjega]);
                ponavljanje_enakofrekvencnih--;
            }
            lokacija_najvecjega++;
        }
        System.out.println();
    }

    /**
     * Vrne najvecje stevilo in tabele
     * @param stevila Opazovana tabela
     * @return Najvecje stevilo
     */
    public static int vrniNajvecje(int[] stevila) {
        int najvecje = Integer.MIN_VALUE;
        for (int stevilo : stevila) {
            if (stevilo > najvecje) {
                najvecje = stevilo;
            }
        }
        return najvecje;
    }

    /**
     * Vrne drugo najmanjse stevilo iz tabele
     * @param stevila Opazovana tabela
     */
    public static void vrniDrugoNajmanjso(int[] stevila) {
        if (stevila.length < 2) {
            System.out.println("Drugo najmanjse stevilo ne obstaja");
            return;
        }
        int[] stevila_sort = stevila.clone();
        Arrays.sort(stevila_sort);
        int dolzina_distinct_arr = vrniDistinctArray(stevila_sort).length;
        int i = 0;
        while (stevila_sort[i] == stevila_sort[0]) {
            i++;
            if(i > dolzina_distinct_arr) {
                System.out.println("Drugo najmanjse stevilo ne obstaja");
                return;
            }
        }
        System.out.printf("Drugo najmanjse stevilo je %d\n\n", stevila_sort[i]);
    }

    /**
     * Vrne povprecje (oz. aritmeticno sredino) vseh stevil v tabeli
     * @param stevila Opazovana tabela
     * @return Povprecje stevil
     */
    public static double vrniPovprecje(int[] stevila) {
        double skupno = 0;
        for (int stevilo : stevila) {
            skupno += stevilo;
        }
        return skupno / stevila.length;
    }

    /**
     * Izračuna varianco (verjetnost distribucije) vseh stevil iz tabele
     * @param stevila Opazovana tabela
     * @return Stevilo varianca
     */
    public static double vrniVarianco(int[] stevila) {
        double vsota = 0;
        double povprecje = vrniPovprecje(stevila);
        for (int stevilo : stevila) {
            vsota += Math.pow((stevilo - povprecje), 2);
        }
        return vsota / stevila.length;
    }

    /**
     * Izracuna standardno deviacijo, potrebna za varianco
     * @param stevila Opazovana tabela
     * @return Stevilo standardna deviacija
     */
    public static double stdDeviacija(int[] stevila) {
        return Math.sqrt(vrniVarianco(stevila));
    }

    /**
     * Izracuna mediano stevil v tabeli
     * @param stevila Opazovana tabela
     * @return Stevilo mediana
     */
    public static double vrniMediano(int[] stevila) {
        double mediana;
        if (stevila.length % 2 == 0) {
            int indeks_spodnjega = (stevila.length / 2) - 1; // -1 zato ker se array zacne z 0
            int indeks_zgornjega = indeks_spodnjega + 1;

            mediana = (stevila[indeks_spodnjega] + stevila[indeks_zgornjega]) / 2.0;
        }
        else {
            if (stevila.length == 1) {
                return 1;
            }
            int indeks_srednjega = (int) Math.ceil(stevila.length / 2.0);
            mediana = stevila[indeks_srednjega];
        }
        return mediana;
    }

    /**
     * Sesteje vsa stevila
     * @param stevila Opazovana tabela
     * @return vsota vseh stevil v tabel
     */
    public static int vrniVsoto(int[] stevila) {
        int vsota = 0;
        for (int stevilo : stevila) {
            vsota += stevilo;
        }
        return vsota;
    }

    /**
     * Ugotovi ali je stevilo palindrom (je isto, ni vazno s katere strani beremo stevke)
     * vir: <a href="https://www.javatpoint.com/how-to-check-palindrome-string-in-java">javapoint.com</a>
     * @param stevilo Opazovano stevilo
     * @return True ali False
     */
    public static boolean jePalindrom(int stevilo) {
        String original = Integer.toString(Math.abs(stevilo));
        String reversed = new StringBuilder(original).reverse().toString();
        return original.equals(reversed);
    }

    /**
     * Steje koliko stevil iz tabele je palindromnih
     * @param stevila Opazovana tabela stevil
     * @return Stevilo palindromnih stevil
     */
    public static int vrniStPalindromnih(int[] stevila) {
        int stevec = 0;

        for (int stevilo : stevila) {
            if (jePalindrom(stevilo)) {
                stevec++;
            }
        }

        return stevec;
    }

    /**
     * Najde najvecji palindrom, ki je manjsi od najvecjega stevila v tabeli
     * @param stevila Opazovana tabela
     */
    public static void maxMozenPalindrom(int[] stevila) {
        int najvecje = vrniNajvecje(stevila);
        boolean overflow_safe = false;
        int palindrom = 0;
        for (int i = najvecje - 1; i > Integer.MIN_VALUE; i--) {
            if(jePalindrom(i)) {
                palindrom = i;
                overflow_safe = true;
                break;
            }
        }

        if (overflow_safe) {
            System.out.println("Najvecji mozni palindom manjsi od najvecjega stevila: " + palindrom);
        }
        else {
            System.out.println("Najvecji mozni palindom manjsi od najvecjega stevila je manjsi od dovoljenega stevilskega obmocja");
        }
        System.out.println();
    }

    /**
     * Izpise tabelo v obratnem vrstnem redu
     * @param stevila Izpisana tabela
     */
    public static void obratenIzpis(int[] stevila) {
        System.out.println("Vsa stevila v obratnem vrstnem redu:");
        for (int i = stevila.length - 1; i > 0; i--) {
            System.out.print(stevila[i] + ", ");
        }
        System.out.print(stevila[0] + "\n");
    }
}