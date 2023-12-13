package de;
import java.util.Random;
import java.util.Scanner;

public class Roulette {
	public static boolean enthält(int[] rot, int ergebnis) {
		boolean gewonnen = false;

		for (int i = 0; i < rot.length; i++) { //Schleife durchläuft Array rot. Wenn der Wert ergebnis gleich einem der
			if (rot[i] == ergebnis) {          // Werte des Arrays ist wird gewonnen true. Wert von gewonnen wird am Ende der Methode zurückgeliefert
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
			Thread.sleep(300);
		}
	}

	public static void main(String[] args) throws InterruptedException{
		int spielgeld = 1000;
		Random rand = new Random();
		int einsatz = 0;
		int [] rot = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
		boolean spielen = true;
		while (spielen == true && spielgeld > (-1000))
		{
			if (spielgeld >= 0)
			{
				System.out.println("\u001B[32mAktueller Kontostand: " + spielgeld + "€\u001B[0m");
			}
			else
			{
				System.out.println("\u001B[31mAktueller Kontostand: " + spielgeld + "€\u001B[0m");
			}
			System.out.println("Auf was möchtest du setzen? Zahl oder Farbe");
			Scanner eingabewert = new Scanner(System.in);
			String wahl = eingabewert.nextLine();
			if (wahl.equalsIgnoreCase("Zahl"))
			{
				System.out.println("Auf welche Zahl möchtest du setzen? (0 - 36)");
				int zahl = eingabewert.nextInt();
				eingabewert.nextLine();
				if (zahl >= 0 && zahl <= 36)
				{
					System.out.println("Wie hoch ist der Geldeinsatz?");
					einsatz = eingabewert.nextInt();
					drehen();
					eingabewert.nextLine();
					int ergebnis = rand.nextInt(36);
					Thread.sleep(1200);
					if (zahl == ergebnis)
					{
						System.out.println("Du hast gewonnen!");
						spielgeld = spielgeld + einsatz * 36;
						System.out.println("\u001B[32mDer Gewinn beträgt: " + einsatz * 36 + "€\u001B[0m");
					}
					else
					{
						System.out.println("Leider verloren!");
						Thread.sleep(300);
						if (enthält(rot, ergebnis) == true)
						{
							System.out.println("Die Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
						else if (enthält(rot, ergebnis) == false && ergebnis != 0)
						{
							System.out.println("Die Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
						else {
							System.out.println("Die Kugel liegt auf \u001B[32m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
					}

				}
				else
				{
					System.out.println("Die Zahl ist nicht zugelassen!");
				}

			}
			else if (wahl.equalsIgnoreCase("Farbe"))
			{
				System.out.println("Auf welche Farbe möchtest du setzen?");
				String farbe = eingabewert.nextLine();
				if (farbe.equalsIgnoreCase("Rot"))
				{
					System.out.println("Wie hoch ist der Geldeinsatz?");
					einsatz = eingabewert.nextInt();
					eingabewert.nextLine();
					drehen();
					int ergebnis = rand.nextInt(36);
					Thread.sleep(1200);
					if (enthält(rot, ergebnis) == true)
					{
						System.out.println("Du hast gewonnen!");
						Thread.sleep(300);
						System.out.println("Die Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
						spielgeld = spielgeld + einsatz * 2;
						System.out.println("\u001B[32mDer Gewinn beträgt: " + einsatz * 2 + "€\u001B[0m");

					}
					else
					{
						System.out.println("Du hast leider verloren");
						Thread.sleep(300);
						if (ergebnis != 0)
						{
							System.out.println("Die Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
						else
						{
							System.out.println("Die Kugel liegt auf \u001B[32m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
					}

				}
				else if (farbe.equalsIgnoreCase("Schwarz"))
				{
					System.out.println("Wie hoch ist der Geldeinsatz?");
					einsatz = eingabewert.nextInt();
					eingabewert.nextLine();
					drehen();
					int ergebnis = rand.nextInt(36);
					Thread.sleep(1200);
					if (enthält(rot, ergebnis) == false)
					{
						System.out.println("Du hast gewonnen!");
						Thread.sleep(300);
						System.out.println("Die Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
						spielgeld = spielgeld + einsatz * 2;
						System.out.println("\u001B[32mDer Gewinn beträgt: " + einsatz * 2 + "€\u001B[0m");
					}
					else
					{
						System.out.println("Du hast leider verloren");
						Thread.sleep(300);
						if (ergebnis != 0)
						{
							System.out.println("Die Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
						else
						{
							System.out.println("Die Kugel liegt auf \u001B[32m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
					}

				}
				else if (farbe.equalsIgnoreCase("Grün"))
				{
					System.out.println("Wie hoch ist der Geldeinsatz?");
					einsatz = eingabewert.nextInt();
					eingabewert.nextLine();
					drehen();
					int ergebnis = rand.nextInt(36);
					boolean gewonnen = false;
					if (ergebnis == 0)
					{
						gewonnen = true;
					}
					else
					{
						gewonnen = false;
					}
					Thread.sleep(1200);
					if (gewonnen = true)
					{
						System.out.println("Du hast gewonnen!");
						Thread.sleep(300);
						System.out.println("Die Kugel liegt auf \u001B[32m" + ergebnis + "\u001B[0m.");
						spielgeld = spielgeld + einsatz * 2;
						System.out.println("\u001B[32mDer Gewinn beträgt: " + einsatz * 2 + "€\u001B[0m");
					}
					else
					{
						System.out.println("Du hast leider verloren");
						Thread.sleep(300);
						if (enthält(rot, ergebnis) == true)
						{
							System.out.println("Die Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
						else
						{
							System.out.println("Die Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
							spielgeld = spielgeld - einsatz;
							System.out.println("\u001B[31mDer Verlust beträgt: -" + einsatz + "€\u001B[0m");
						}
					}

				}
				else
				{
					System.out.println("Eine fehlerhafte Eingabe wurde getätigt!");
				}
			}
			else
			{
				System.out.println("Eine fehlerhafte Eingabe wurde getätigt!");
			}
			Thread.sleep(1800);
			System.out.println("Möchtest du weiterspielen?");
			Thread.sleep(600);
			String antwort = eingabewert.nextLine();

			if (antwort.equalsIgnoreCase("Ja")) {
				einsatz = 0;
				spielen = true;
			} else if (antwort.equalsIgnoreCase("Nein")) {
				spielen = false;
			} else {
				System.out.println("Fehlerhafte Eingabe");
				Thread.sleep(1800);
				return;
			}
		}
	}
}