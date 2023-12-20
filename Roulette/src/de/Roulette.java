package de;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Kontrolle Kontostand hinzugefügt und in methode verlagert
public class Roulette {

	private static boolean play = true;
	private static String[] gameSelection = {"zahl", "farbe"};
	private static String[] colourSelection = {"rot","schwarz","grün"};
	private static final HashMap <String, String> colourDots = new HashMap<>();
	static {
		colourDots.put("🟢", "grün");
		colourDots.put("🔴", "rot");
		colourDots.put("⚫", "schwarz");
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

		System.out.println("⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫");
		System.out.println();
		System.out.println("--- Herzlich Willkommen zu unserem Roulette ---");
		System.out.println();
		System.out.println("⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫🔴⚫");
		System.out.println();


		Thread.sleep(1000);
		System.out.println("Setze auf eine beliebige Zahl zwischen 0-36 oder \nauf die Farben Schwarz, Rot oder Grün und gewinne!\n");
		Thread.sleep(1000);

		System.out.println("-------------------------------------------\n");
		System.out.println("Ihre Gewinnchancen und möglichen Gewinne:\n");
		System.out.println("⚫ Schwarz \t\tGewinnchance: 48,6%\tGewinn: 1:1");
		System.out.println("🔴 Rot \t\t\tGewinnchance: 48,6%\tGewinn: 1:1");
		System.out.println("🟢 Grün \t\tGewinnchance: 2,7%\tGewinn: 35:1");
		System.out.println("⬤ Beliebige Zahl\tGewinnchance: 2,7%\tGewinn: 35:1");
		System.out.println();
		System.out.println("-------------------------------------------\n");


		try (Scanner scanner = new Scanner(System.in)){
			int account = addToAccount(scanner);		
			printAccount(account);
			roulette(account, scanner);
		} catch (Exception e) {
			System.out.println("Es gab einen Fehler. Bitte Spiel neu starten!");
		}
	}

	public static void roulette(int account, Scanner scanner) throws InterruptedException{
		Random rand = new Random();
		scanner.nextLine();

		while (play == true && account > 0) {

			// Spielauswahl Nutzer
			System.out.println("Auf was möchtest du setzen? Zahl oder Farbe");
			String eingabe = scanner.nextLine();

			// Prüfen der Eingabe
			while (isInputValid(eingabe.trim(), gameSelection) != true) {
				System.out.println("Fehlerhafte Eingabe! Bitte Zahl oder Farbe angeben.");
				eingabe = scanner.nextLine();
				isInputValid(eingabe,gameSelection);
			}

			// Programmauswahl
			if (eingabe.trim().equalsIgnoreCase("Zahl")) {
				account = gameNumber(scanner, account, rand);			
			} else if (eingabe.trim().equalsIgnoreCase("Farbe")) {
				account = gameColour(scanner, account, rand);
			}

			printAccount(account);

			if (account <= 0) {
				System.out.println("Du bist pleite!");
				System.out.println("Danke fürs Spielen!");
				return;
			}

			// Neues Spiel?
			Thread.sleep(600);
			System.out.println("Möchtest du weiterspielen? (Ja / Nein)");
			String playAgain = scanner.nextLine();
			scanner.reset();

			if (playAgain == null) {
				playAgain = scanner.nextLine();
			}

			while (newGame(scanner,playAgain) != true) {
				Thread.sleep(400);
				System.out.println("Fehlerhafte Eingabe! Bitte Ja oder Nein angeben.");
				playAgain = scanner.nextLine();
				newGame(scanner, playAgain);
			}
		}

		// Ende 
		System.out.println();
		printAccount(account);
		System.out.println("Danke fürs Spielen!");
	}

	private static void printAccount(int account) {
		if (account > 0) {
			System.out.println("\u001B[32mAktueller Kontostand: " + account + "€\u001B[0m");
			System.out.println();
		} else {
			System.out.println("\u001B[31mAktueller Kontostand: " + account + "€\u001B[0m");
			System.out.println();
		}
	}

	private static int gameColour(Scanner scanner, int account, Random rand) throws InterruptedException {

		System.out.println("Auf welche Farbe möchtest du setzen? (Rot / Schwarz / Grün)");
		String eingabe = scanner.nextLine();

		while (isInputValid(eingabe, colourSelection) != true) {
			System.out.println("Fehlerhafte Eingabe! Bitte Rot, Schwarz oder Grün angeben.");
			eingabe = scanner.nextLine();
			isInputValid(eingabe, colourSelection);
		}

		int einsatz = placeBet(scanner, account);
		account = account - einsatz;

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
		printColourAnimation(startPosition, winningNumber);

		Thread.sleep(1000);

		if (eingabe.equals("grün") && eingabe.equals(colourDots.get(winningColour))) {
			account = win(account, einsatz, 36);
		} else if (eingabe.equals(colourDots.get(winningColour))) {
			account = win(account, einsatz, 2);
		} else {
			lose(einsatz);
		}
		scanner.nextLine();
		return account;
	} 

	private static int gameNumber(Scanner scanner, int account, Random rand) throws InterruptedException {

		System.out.println("Auf welche Zahl möchtest du setzen? (0 - 36)");

		int eingabe = checkNumberInput(scanner);
		
		while (isNumberValid(eingabe) != true) {
			System.out.println("Fehlerhafte Eingabe! Bitte eine Zahl zwischen 0 und 36 angeben");
			eingabe = checkNumberInput(scanner);
			isNumberValid(eingabe);
		}

		int einsatz = placeBet(scanner, account);
		account = account - einsatz;

		printNumberAnimation();

		scanner.nextLine();
		int ergebnis = rand.nextInt(36);
		Thread.sleep(1200);
		
		// Farbliches Ausgeben des Ergebnisses
		printWinningNumber(ergebnis);

		if (eingabe == ergebnis) {
			account = win(account, einsatz, 36);
		} else {
			lose(einsatz);
		}

		return account;
	}

	private static boolean isNumberValid(int eingabe) {	
		if (eingabe > 36 || eingabe < 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isAccountInputValid(int eingabe) {	
		if (eingabe > 1000 || eingabe < 1) {
			return false;
		} else {
			return true;
		}
	}

	private static int placeBet(Scanner scanner, int account) {
		System.out.println("Wie hoch ist der Geldeinsatz?");
		int einsatz = scanner.nextInt();

		while(einsatz <= 0) {
			System.out.println("Bitte positiven / gültigen Betrag eingeben!");			
			einsatz = scanner.nextInt();
		}

		while (isBetValid(account, einsatz) != true) {
			System.out.println("Konto überzogen! Bitte kleineren Betrag angeben");
			einsatz = scanner.nextInt();
			isBetValid(account, einsatz);
		}

		return einsatz;
	}

	public static boolean isInputValid(String eingabe, String[] ergebnis) {	
		for (String string : ergebnis) {
			if (eingabe.trim().equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;		
	}

	public static boolean isBetValid(int account, int einsatz) {	
		if (einsatz > account) {
			return false;
		} else {
			return true;
		}
	}

	public static int addToAccount(Scanner scanner) {	
		System.out.println("Wie viel möchten Sie aufladen? (1-1000€ möglich)");
		int eingabe = checkAccountInput(scanner);
		
		while (isAccountInputValid(eingabe) == false) {
			System.out.println("Bitte einen Betrag zwischen 1€ und 1000€ eingeben:");
			eingabe = checkAccountInput(scanner);
			isAccountInputValid(eingabe);
		}
		System.out.println("");

		return eingabe;
	}

	private static int checkAccountInput(Scanner scanner) {
		int eingabe = 0;
		while (isAccountInputValid(eingabe) == false) {
			try{
				eingabe = scanner.nextInt();
				if (eingabe > 1000 || eingabe < 1  ) {
					System.out.println("Bitte einen Wert zwischen 1 und 1000 angeben");
				}
			} catch(Exception ex) {
				System.out.println("Bitte eine Zahl eingeben!");
				scanner.next();
				continue;
			}
		} 
		if (isAccountInputValid(eingabe)== false) {
			System.out.println("Bitte Zahl zwischen 1 und 1000 eingeben:");
			checkAccountInput(scanner);
		}
		return eingabe;		
	}
	
	private static int checkNumberInput(Scanner scanner) {
		int eingabe = -1;
		while (isNumberValid(eingabe)==false) {
			try{
				eingabe = scanner.nextInt();
				if (eingabe > 36 || eingabe < 0) {
					System.out.println("Bitte einen Wert zwischen 0 und 36 angeben");
				}
			}catch(Exception ex){
				System.out.println("Bitte eine Zahl eingeben!");
				scanner.next();
				continue;
			}
		}
		return eingabe;		
	}

	private static void printColourAnimation(int startPosition, int winningNumber) throws InterruptedException {

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

	private static void printNumberAnimation() throws InterruptedException{
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			System.out.println();
			System.out.print("\rDie Kugel dreht sich... " + random.nextInt(36));
			Thread.sleep(400);
		}
	}

	private static void printWinningNumber(int ergebnis) {
		System.out.println();
		if (wheel.get(ergebnis).equals("🔴")) {
			System.out.println("\rDie Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
		} else if (wheel.get(ergebnis).equals("⚫")) {
			System.out.println("\rDie Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
		} else {
			System.out.println("\rDie Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
		}
	}

	private static int win(int account, int einsatz, int multiplier) throws InterruptedException {
		System.out.println();
		System.out.println("\rDu hast gewonnen!");
		Thread.sleep(400);
		System.out.println("\u001B[32mDer Gewinn beträgt: " + einsatz * multiplier + "€\u001B[0m");
		account = account + (einsatz * multiplier);
		return account;
	}

	private static void lose(int einsatz) throws InterruptedException {
		System.out.println();
		System.out.println("\rDu hast leider verloren");
		Thread.sleep(400);
		System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
	}

	private static boolean newGame(Scanner scanner, String antwort) {
		if (antwort.trim().equalsIgnoreCase("Ja") || antwort.trim().equalsIgnoreCase("J")) {
			play = true;
			return true;
		} else if (antwort.trim().equalsIgnoreCase("Nein") || antwort.trim().equalsIgnoreCase("N")) {
			play = false;
			return true;
		} else {
			return false;			
		}
	}
}