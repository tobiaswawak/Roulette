package de;
import java.util.Random;
import java.util.Scanner;

public class Roulette {

    private static boolean spielen = true;
    private static String[] spiel = {"zahl", "farbe"};
    private static String[] farben = {"rot","schwarz","grün"};
    private static int [] rot = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    public static void main(String[] args) throws InterruptedException{
        int einsatz = 0;
        int konto = 1000;

        System.out.println("--------------------------------------------------");
        System.out.println("    Herzlich Willkommen in unserem Casino	");
        System.out.println("--------------------------------------------------");
        System.out.println();

        roulette(konto, einsatz, rot);
    }

    public static void roulette(int konto, int einsatz, int[] rot) throws InterruptedException{
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        while (spielen == true) {

            kontostandAusgeben(konto);

            // Spielauswahl Nutzer
            System.out.println("Auf was möchtest du setzen? Zahl oder Farbe");
            String eingabe = scanner.nextLine();

            // Prüfen der Eingabe
            while (eingabeKorrekt(eingabe,spiel) != true) {
                System.out.println("Fehlerhafte Eingabe! Bitte Zahl oder Farbe angeben.");
                eingabe = scanner.nextLine();
                eingabeKorrekt(eingabe,spiel);
            }

            // Programmauswahl
            if (eingabe.equalsIgnoreCase("Zahl")) {
                konto = spielZahl(scanner, konto, einsatz, rot, rand);
            } else if (eingabe.equalsIgnoreCase("Farbe")) {
                konto = spielFarbe(scanner, konto, einsatz, rot, rand);
            }

            // Neues Spiel?
            Thread.sleep(600);
            System.out.println("\rMöchtest du weiterspielen? (J/N)");

            eingabe = scanner.nextLine();
            if (eingabe != null ) {
                eingabe = scanner.nextLine();
            }

            while (neuesSpiel(scanner,eingabe) != true) {
                System.out.println("Fehlerhafte Eingabe! Bitte Ja oder Nein angeben.");
                eingabe = scanner.nextLine();
                neuesSpiel(scanner, eingabe);
            }
        }

        // Ende
        kontostandAusgeben(konto);
        System.out.println("\rDanke fürs Spielen!");
    }

    private static void kontostandAusgeben(int konto) {
        if (konto >= 0) {
            System.out.println("\u001B[32mAktueller Kontostand: " + konto + "€\u001B[0m");
            System.out.println();
        } else {
            System.out.println("\u001B[31mAktueller Kontostand: " + konto + "€\u001B[0m");
            System.out.println();
        }
    }

    private static int spielFarbe(Scanner scanner, int konto, int einsatz, int[] rot, Random rand) throws InterruptedException {

        System.out.println("Auf welche Farbe möchtest du setzen? (Rot / Schwarz / Grün)");
        String eingabe = scanner.nextLine();

        while (eingabeKorrekt(eingabe, farben) != true) {
            System.out.println("Fehlerhafte Eingabe! Bitte Rot, Schwarz oder Grün angeben.");
            eingabe = scanner.nextLine();
            eingabeKorrekt(eingabe, farben);
        }

        System.out.println("Wie hoch ist der Geldeinsatz?");
        einsatz = scanner.nextInt();
        konto = konto - einsatz;
        drehen();
        int ergebnis = rand.nextInt(36);
        Thread.sleep(1200);

        // Farbliches Ausgeben des Ergebnisses
        kugelAuf(ergebnis);

        if (eingabe.equalsIgnoreCase("Rot")) {
            if (arrayEnthaelt(rot, ergebnis) == true) {
                konto = gewonnen(konto, einsatz, 2);
            } else {
                verloren(konto, einsatz);
            }
        } else if (eingabe.equalsIgnoreCase("Schwarz")) {
            if (arrayEnthaelt(rot, ergebnis) == false && ergebnis != 0) {
                konto = gewonnen(konto, einsatz, 2);
            } else {
                verloren(konto, einsatz);
            }

        } else if (eingabe.equalsIgnoreCase("Grün")) {
            Thread.sleep(1200);
            if (ergebnis == 0) {
                konto = gewonnen(konto, einsatz, 35);
            } else {
                verloren(konto, einsatz);
            }

        } else {
            System.out.println("Eine fehlerhafte Eingabe wurde getätigt!");
            spielFarbe(scanner, konto, einsatz, rot, rand);
        }
        return konto;
    }

    private static int spielZahl(Scanner scanner, int konto, int einsatz, int[] rot, Random rand) throws InterruptedException {

        System.out.println("Auf welche Zahl möchtest du setzen? (0 - 36)");
        int eingabe = scanner.nextInt();

        if (eingabe >= 0 && eingabe <= 36) {
            System.out.println("Wie hoch ist der Geldeinsatz?");
            einsatz = scanner.nextInt();
            konto = konto - einsatz;

            drehen();

            scanner.nextLine();
            int ergebnis = rand.nextInt(36);
            Thread.sleep(1200);

            // Farbliches Ausgeben des Ergebnisses
            kugelAuf(ergebnis);

            if (eingabe == ergebnis) {
                konto = gewonnen(konto, einsatz, 36);
            } else {
                verloren(konto, einsatz);
            }

        } else {
            System.out.println("Die Zahl ist nicht zugelassen!");
            spielZahl(scanner, konto, einsatz, rot, rand);
        }
        return konto;
    }

    public static boolean eingabeKorrekt(String eingabe, String[] ergebnis) {
        for (String string : ergebnis) {
            if (eingabe.equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    public static boolean arrayEnthaelt (int[] rot, int ergebnis) {
        boolean gewonnen = false;
        //Schleife durchläuft Array rot. Wenn der Wert ergebnis gleich einem der
        for (int i = 0; i < rot.length; i++) {
            // Werte des Arrays ist wird gewonnen true. Wert von gewonnen wird am Ende der Methode zurückgeliefert
            if (rot[i] == ergebnis) {
                gewonnen = true;
            }
        }
        return gewonnen;
    }

    private static void drehen() throws InterruptedException{
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            System.out.print("\rDie Kugel dreht sich... " + random.nextInt(36));
            System.out.println();
            Thread.sleep(400);
        }
    }

    private static void kugelAuf(int ergebnis) {
        if (arrayEnthaelt(rot, ergebnis) == true) {
            // rot
            System.out.println("\rDie Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
        } else if (arrayEnthaelt(rot, ergebnis) == false && ergebnis != 0) {
            // schwarz
            System.out.println("\rDie Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
        } else {
            // grün
            System.out.println("\rDie Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
        }
    }

    private static int gewonnen(int konto, int einsatz, int multiplier) throws InterruptedException {

        System.out.println();
        System.out.println("Du hast gewonnen!");
        Thread.sleep(300);
        System.out.println("\u001B[32mDer Gewinn beträgt: " + einsatz * 2 + "€\u001B[0m");
        konto = konto + (einsatz * multiplier);
        return konto;
    }

    private static void verloren(int konto, int einsatz) throws InterruptedException {
        System.out.println("Du hast leider verloren");
        Thread.sleep(300);
        System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
    }

    private static boolean neuesSpiel(Scanner scanner, String antwort) {

        if (antwort.equalsIgnoreCase("Ja") || antwort.equalsIgnoreCase("J")) {
            spielen = true;
            return true;
        } else if (antwort.equalsIgnoreCase("Nein") || antwort.equalsIgnoreCase("N")) {
            spielen = false;
            return true;
        } else {
            return false;
        }

    }

}