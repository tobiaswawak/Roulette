package de;

import java.util.Random;
import java.util.Scanner;

public class Roulette {
	public static boolean enthält(int[] rot, int ergebnis) {
		boolean gewonnen = false;

		for (int i = 0; i < rot.length; i++) {
			if (rot[i] == ergebnis) {
				gewonnen = true;
			}
		}
		return gewonnen;
	}

	private static void drehen() throws InterruptedException{
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			System.out.print("\rDie Kugel dreht sich... " + random.nextInt(37));
			System.out.println();
			Thread.sleep(300);
		}
	}




	public static void main(String[] args) throws InterruptedException{
		Random rand = new Random();
		int [] rot = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
		System.out.println("Auf was möchtest du setzen? Zahl oder Farbe");
		Scanner eingabewert = new Scanner(System.in);
		String wahl = eingabewert.nextLine();
		if (wahl.equalsIgnoreCase("Zahl")) {
			System.out.println("Auf welche Zahl möchtest du setzen? (0 - 36)");
			int zahl = eingabewert.nextInt();
			if (zahl >= 0 && zahl <= 36) {
				drehen();
				int ergebnis = rand.nextInt(36);
				ergebnis = 0;
				Thread.sleep(1200);
				if (zahl == ergebnis) {
					System.out.println("Du hast gewonnen!");
				} else {
					System.out.println("Leider verloren!");
					Thread.sleep(300);
					if (enthält(rot, ergebnis) == true) {
						System.out.println("Die Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
					}
					else if (enthält(rot, ergebnis) == false && ergebnis != 0) {
						System.out.println("Die Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
					}
					else {
						System.out.println("Die Kugel liegt auf \u001B[32m" + ergebnis + "\u001B[0m.");
					}
				}

			}
			else {
				System.out.println("Die Zahl ist nicht zugelassen!");
				return;
			}

		}

		else if(wahl.equalsIgnoreCase("Farbe")) {
			System.out.println("Auf welche Farbe möchtest du setzen?");
			String farbe = eingabewert.nextLine();
			if (farbe.equalsIgnoreCase("Rot")) {
				drehen();
				int ergebnis = rand.nextInt(36);
				Thread.sleep(1200);
				if (enthält(rot, ergebnis) == true) {
					System.out.println("Du hast gewonnen!");
					Thread.sleep(300);
					System.out.println("Die Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
				}
				else {
					System.out.println("Du hast leider verloren");
					Thread.sleep(300);
					if (ergebnis != 0) {
						System.out.println("Die Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
					}
					else {
						System.out.println("Die Kugel liegt auf \u001B[32m" + ergebnis + "\u001B[0m.");
					}
				}

			}
			else if (farbe.equalsIgnoreCase("Schwarz")) {
				drehen();
				int ergebnis = rand.nextInt(36);
				Thread.sleep(1200);
				if (enthält(rot, ergebnis) == false) {
					System.out.println("Du hast gewonnen!");
					Thread.sleep(300);
					System.out.println("Die Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
				}
				else {
					System.out.println("Du hast leider verloren");
					Thread.sleep(300);
					if (ergebnis != 0) {
						System.out.println("Die Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
					}
					else {
						System.out.println("Die Kugel liegt auf \u001B[32m" + ergebnis + "\u001B[0m.");
					}
				}

			}
			else if (farbe.equalsIgnoreCase("Grün")) {
				drehen();
				int ergebnis = rand.nextInt(36);
				boolean gewonnen = false;
				if (ergebnis == 0) {
					gewonnen = true;
				}
				else {
					gewonnen = false;
				}
				Thread.sleep(1200);
				if (gewonnen = true) {
					System.out.println("Du hast gewonnen!");
					Thread.sleep(300);
					System.out.println("Die Kugel liegt auf \u001B[32m" + ergebnis + "\u001B[0m.");
				}
				else {
					System.out.println("Du hast leider verloren");
					Thread.sleep(300);
					if (enthält(rot, ergebnis) == true) {
						System.out.println("Die Kugel liegt auf \u001B[31m" + ergebnis + "\u001B[0m.");
					}
					else {
						System.out.println("Die Kugel liegt auf \u001B[30m" + ergebnis + "\u001B[0m.");
					}
				}

			}
			else {
				System.out.println("Eine fehlerhafte Eingabe wurde getätigt!");
			}
		}
		else {
			System.out.println("Eine fehlerhafte Eingabe wurde getätigt!");
		}


	}
}