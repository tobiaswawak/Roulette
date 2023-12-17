package de;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Kontrolle Kontostand hinzugefügt und in methode verlagert

public class Roulette {

	private static boolean spielen = true;
	private static int[] rot = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
	private static String[] spiel = {"zahl", "farbe"};
	private static String[] farben = {"rot","schwarz","grün"};
	private static final HashMap <String, String> farbPunkte = new HashMap<>();
	static {
		farbPunkte.put("🟢", "grün");
		farbPunkte.put("🔴", "rot");
		farbPunkte.put("⚫", "schwarz");
	}

	private static final HashMap<Integer, String> wheel = new HashMap<>();
	static {
		wheel.put(0, "🟢");
		wheel.put(1, "🔴");
		wheel.put(2, "⚫");
		wheel.put(3, "🔴");
		wheel.put(4, "⚫");
		wheel.put(5, "🔴");
		wheel.put(6, "⚫");
		wheel.put(7, "🔴");
		wheel.put(8, "⚫");
		wheel.put(9, "🔴");
		wheel.put(10, "⚫");
		wheel.put(11, "🔴");
		wheel.put(12, "⚫");
		wheel.put(13, "🔴");
		wheel.put(14, "⚫");
		wheel.put(15, "🔴");
		wheel.put(16, "⚫");
		wheel.put(17, "🔴");
		wheel.put(18, "⚫");
		wheel.put(19, "🔴");
		wheel.put(20, "⚫");
		wheel.put(21, "🔴");
		wheel.put(22, "⚫");
		wheel.put(23, "🔴");
		wheel.put(24, "⚫");
		wheel.put(25, "🔴");
		wheel.put(26, "⚫");
		wheel.put(27, "🔴");
		wheel.put(28, "⚫");
		wheel.put(29, "🔴");
		wheel.put(30, "⚫");
		wheel.put(31, "🔴");
		wheel.put(32, "⚫");
		wheel.put(33, "🔴");
		wheel.put(34, "⚫");
		wheel.put(35, "🔴");
		wheel.put(36, "⚫");
	}

	public static void main(String[] args) throws InterruptedException{

		System.out.println("--------------------------------------------------");
		System.out.println("    Herzlich Willkommen in unserem Casino	");
		System.out.println("--------------------------------------------------");
		System.out.println();
		
		System.out.println("Wir bieten Ihnen die Möglichkeit auf eine beliebige Zahl zwischen 0-36 zu setzen\noder auf die Farben Schwarz, Rot oder Grün.\n");
		System.out.println("Ihre Gewinnchancen und möglichen Gewinne:\n");
		System.out.println("Schwarz\t\tGewinnchance: 48,6%\tGewinn: 1:1");
		System.out.println("Rot\t\tGewinnchance: 48,6%\tGewinn: 1:1");
		System.out.println("Grün\t\tGewinnchance: 2,7%\tGewinn: 35:1");
		System.out.println("Beliebige Zahl\tGewinnchance: 02,7%\tGewinn: 35:1");
		System.out.println();
	
		try (Scanner scanner = new Scanner(System.in)){
			int konto = kontoAufladen(scanner);		
			kontostandAusgeben(konto);
			roulette(konto, scanner);
		} catch (Exception e) {
			System.out.println("Es gab einen Fehler. Bitte Spiel neustarten!");
		}
	}

	public static void roulette(int konto, Scanner scanner) throws InterruptedException{
		Random rand = new Random();
		String empty = scanner.nextLine();
		
		while (spielen == true) {

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
				konto = spielZahl(scanner, konto, rand);			
			} else if (eingabe.equalsIgnoreCase("Farbe")) {
				konto = spielFarbe(scanner, konto, rand);
			}
			
			kontostandAusgeben(konto);

			// Neues Spiel?
			Thread.sleep(600);
			System.out.println("Möchtest du weiterspielen? (Ja / Nein)");
			empty = scanner.nextLine();
			String erneutSpielen = scanner.nextLine();

			if (erneutSpielen == null) {
				erneutSpielen = scanner.nextLine();
			}

			while (neuesSpiel(scanner,erneutSpielen) != true) {
				Thread.sleep(400);
				System.out.println("Fehlerhafte Eingabe! Bitte Ja oder Nein angeben.");
				erneutSpielen = scanner.nextLine();
				neuesSpiel(scanner, erneutSpielen);
			}
		}

		// Ende 
		System.out.println();
		kontostandAusgeben(konto);
		System.out.println("Danke fürs Spielen!");
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

	private static int spielFarbe(Scanner scanner, int konto, Random rand) throws InterruptedException {

		System.out.println("Auf welche Farbe möchtest du setzen? (Rot / Schwarz / Grün)");
		String eingabe = scanner.nextLine();

		while (eingabeKorrekt(eingabe, farben) != true) {
			System.out.println("Fehlerhafte Eingabe! Bitte Rot, Schwarz oder Grün angeben.");
			eingabe = scanner.nextLine();
			eingabeKorrekt(eingabe, farben);
		}

		int einsatz = geldSetzen(scanner, konto);
		konto = konto - einsatz;
		
		// Zufälige Startpositin und Endposition
		Random randomizer = new Random();
		int startPosition = randomizer.nextInt(0, 37);
		int rollCount = randomizer.nextInt(20, 30);

		// Errechnen der Gewinnzahl
		int winningNumber = startPosition + rollCount;

		if(winningNumber > 36) {
			winningNumber = winningNumber - 37;
		}

		String winningColour = wheel.get(winningNumber);
		
		System.out.println("");
		System.out.println("Drehe das Roulette-Rad: ");

		Thread.sleep(1000);
		
		// Ausgabe von Startposition		
		ausgabeFarben(startPosition, winningNumber);

		Thread.sleep(1000);

		if (eingabe.equals("grün") && eingabe.equals(farbPunkte.get(winningColour))) {
			konto = gewonnen(konto, einsatz, 36);
		} else if (eingabe.equals(farbPunkte.get(winningColour))) {
			konto = gewonnen(konto, einsatz, 2);
		} else {
			verloren(einsatz);
		}
		return konto;
	} 

	private static int spielZahl(Scanner scanner, int konto, Random rand) throws InterruptedException {

		System.out.println("Auf welche Zahl möchtest du setzen? (0 - 36)");
		int eingabe = scanner.nextInt();

		if (eingabe >= 0 && eingabe <= 36) {
			
			int einsatz = geldSetzen(scanner, konto);
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
				verloren(einsatz);
			}

		} else {
			System.out.println("Die Zahl ist nicht zugelassen!");
			spielZahl(scanner, konto, rand);
		}
		return konto;
	}

	private static int geldSetzen(Scanner scanner, int konto) {
		System.out.println("Wie hoch ist der Geldeinsatz?");
		int einsatz = scanner.nextInt();
		
		while (pruefeKontostand(konto, einsatz) != true) {
			System.out.println("Konto überzogen! Bitte kleineren Betrag angeben");
			einsatz = scanner.nextInt();
			pruefeKontostand(konto, einsatz);
		}
		
		return einsatz;
	}

	public static boolean eingabeKorrekt(String eingabe, String[] ergebnis) {	
		for (String string : ergebnis) {
			if (eingabe.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;		
	}
	
	public static boolean pruefeKontostand(int konto, int einsatz) {	
		if (einsatz > konto) {
			return false;
		} else {
			return true;
		}
	}
	
	public static int kontoAufladen(Scanner scanner) {	
		System.out.println("Wie viel möchten Sie aufladen? (1-1000€ möglich)");
		int eingabe = scanner.nextInt();
		
		while (pruefeEingabeKontostand(eingabe) == false) {
			System.out.println("Bitte einen Betrag zwischen 1€ und 1000€ eingeben:");
			eingabe = scanner.nextInt();
			pruefeEingabeKontostand(eingabe);
		}
		System.out.println("");
		
		return eingabe;
	}
	
	public static boolean pruefeEingabeKontostand(int eingabe) {	
		if (eingabe > 1000 || eingabe < 1) {
			return false;
		} else {
			return true;
		}
	}
	
	private static void ausgabeFarben(int startPosition, int winningNumber) throws InterruptedException {

		int currentPos = startPosition;
		
		while (currentPos < 37 && currentPos != winningNumber) {
			System.out.print(wheel.get(currentPos));
			currentPos++;
			Thread.sleep(100);
		} 
		
		if (currentPos == winningNumber) {
			System.out.print(wheel.get(currentPos));
			return;
		}

		currentPos = 0;			
		while (currentPos != winningNumber) {
			System.out.print(wheel.get(currentPos));
			currentPos++;
			Thread.sleep(100);
		}
		Thread.sleep(100);
		System.out.print(wheel.get(currentPos));

	}
	
	public static boolean arrayEnthaelt(int[] rot, int ergebnis) {
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
		System.out.println("\rDu hast gewonnen!");
		Thread.sleep(400);
		System.out.println("\u001B[32mDer Gewinn beträgt: " + einsatz * multiplier + "€\u001B[0m");
		konto = konto + (einsatz * multiplier);
		return konto;
	}

	private static void verloren(int einsatz) throws InterruptedException {
		System.out.println();
		System.out.println("\rDu hast leider verloren");
		Thread.sleep(400);
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