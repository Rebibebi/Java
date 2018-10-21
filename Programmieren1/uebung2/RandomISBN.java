//Rebecca Vogt (Hörerin), Matrikelnummer 03-054-889
//Programmierung 1, Universität Bern, HS 2018
//
//Übung 2-1

import java.text.DecimalFormat;
import java.util.Random;

public class RandomISBN
{

	public static void main( String args[] )
	{
		// Ruft die Methode makeISBN auf und zeigt den Returnwert in der Konsole
		
		System.out.println("1st ISBN: " + makeISBN());
		System.out.println("2nd ISBN: " + makeISBN());
		System.out.println("3rd ISBN: " + makeISBN());

	}

	/** generates and returns a random ISBN in the format XX-XXX-XX-C */
	public static String makeISBN()
	{
		// Do NOT change the declaration of the following variables!
		
		String laendercode;
		String bandnr;
		String verlagsnr;
		String checksum;

		// Erzeugt randomisierte Zahlenwerte
		
		Random generator = new Random();
		int laendercodeInt = generator.nextInt(98)+1;
		int bandnrInt = generator.nextInt(998)+1;
		int verlagsnrInt = generator.nextInt(98)+1;
		int checksumInt = generator.nextInt(9)+1;
		
		// Formatiert Zahlenwerte
		
		DecimalFormat fmt1 = new DecimalFormat("00");
		DecimalFormat fmt2 = new DecimalFormat("000");
		DecimalFormat fmt3 = new DecimalFormat("#");
		
		// Formatiert die Integerwerte als Strings
	
		laendercode = fmt1.format(laendercodeInt);
		bandnr = fmt2.format(bandnrInt);
		verlagsnr = fmt1.format(verlagsnrInt);
		checksum = fmt3.format(checksumInt);
		
		// Do not change the following line
		
		return laendercode + "-" + bandnr + "-" + verlagsnr + "-" + checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp(int i)
	{
		// Do NOT change this method!
		int doubled = 2 * i;
		if (doubled >= 10) {
			doubled = doubled - 9;
		}
		return doubled;
	}
}
