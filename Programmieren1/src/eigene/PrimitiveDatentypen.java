package eigene;

public class PrimitiveDatentypen {
	
	public static void main (String[] args) {
		
		/* Vordefinition der Ergebnisse als primitive Datentypen */
		
		byte einByte;
		short einShort;
		int einInteger;
		long einLong;
		float einFloat;
		double einDouble;
		char einChar;
		boolean einBoolean;
		
		/* Vordefinition der Zähler und Nenner als primitive Datentypen */
		
		byte zaehlerByte = 4;
		byte nennerByte = 3;
		short zaehlerShort = 4;
		short nennerShort = 3;
		long zaehlerLong = 4;
		long nennerLong = 3;
		float zaehlerFloat = 4;
		float nennerFloat = 3;
		double zaehlerDouble = 4;
		double nennerDouble = 3;
		
		
		/* Rechenoperationen */
		
		einByte = zaehlerByte;
		einShort = zaehlerShort;
		einInteger = 4/3;
		einLong = zaehlerLong/nennerLong;
		einFloat = zaehlerFloat/nennerFloat;
		einDouble = zaehlerDouble/nennerDouble;
		
		System.out.println("Ausgabe als Float " + einFloat);
		System.out.println("Ausgabe als Double " + einDouble);
	
		
	}
	
	

}
