//Rebecca Vogt (H�rerin), Matrikelnummer 03-054-889
//Programmierung 1, Universit�t Bern, HS 2018
//
//�bung 1-2


import java.util.Scanner;

public class Divide
{

	public static void main(String[] args)
	{
		double a=1;
		double b=1;
		
		
		Scanner scan = new Scanner(System.in); 
    
		System.out.println("Geben Sie den Z�hler ein, der quadriert wird:"); 
       		
		a = scan.nextDouble();
		
		System.out.println("Geben Sie den Nenner ein:");
		
		b = scan.nextDouble();

		double gleitkomma = (a*a)/b;
		
		int arund = (int) a;
		int brund = (int) b;
		int ergebnisrund = (arund*arund)/brund;
		double rest = gleitkomma-ergebnisrund;
				
		System.out.println("Das Ergebnis mit Gleitkommazahl: " + gleitkomma);
		System.out.print("Das Ergebnis mit gerundeter Zahl: " + ergebnisrund + ", Rest:" + rest);
	}
}