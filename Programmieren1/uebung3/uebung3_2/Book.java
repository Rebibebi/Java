package uebung3_2;

import java.util.Date;
import java.text.*;

public class Book
{
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;
	private int ageBook; // Diese Variable wird in �bung 3-2 nicht verwendet und wird nur zur Hausaufgabenkontrolle im Code belassen!
	private int price;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	//--- constructors ---


	public Book( )
	{
		id = 0;
		title = "unknown";
		author = "unknown";
		dateOfPublication = stringToDate("01.01.1999");
		price = 0;
		
	}
	
	public Book(int pid, String ptitle, String pname, Date pdateOfPublication, int pprice) 
	{
		id = pid;
		title = ptitle;
		author = pname;
		dateOfPublication = pdateOfPublication;
			if (pprice < 0) 
				pprice = 0;
		price = pprice;
	}

	/** Returns the age of the book in days since publication */
	// Diese Methode wird in �bung 3-2 nicht verwendet und wird nur zur Hausaufgabenkontrolle im Code belassen!
	
	public int age()
	{
		Date datAktuell = new Date(); //aktuelles Systemdatum erzeugen
		long datDiff = datAktuell.getTime()-dateOfPublication.getTime(); //Differenz in Millisekunden von aktuellem Datum und Publikationsdatum
		long ageBooklong = datDiff / 86400000;
		ageBook = (int)ageBooklong; //Differenz wird von Milisekunden in Tage umgerechnet
		return ageBook; //int-Wert der Tage wird zur�ckgegeben
	}
	
	
	/** Returns a String representation of the book */
	public String toString()
	{
		String ausgabe = id + ", " + title + ", " + author + ", " + dateToString(dateOfPublication) + ", " + price + " CHF";
		return ausgabe;
	}


	//Setter-Methods
	
	public void setId(int variable1)
	{
		id = variable1;
	}
	
	public void setTitle(String variable2)
	{
		title = variable2;
	}
	
	public void setAuthor(String variable3)
	{
		author = variable3;
	}
	
	public void setDateOfPublication(String variable4)
	{
		Date variable5 = stringToDate(variable4);
		dateOfPublication = variable5;
	}
	
	public void setPrice(int variable6)
	{
			if (variable6 < 0) 
				variable6 = 0;
		price = variable6;
	}
	
	// Getter-Methods
	
	public int getId()
	{
		return id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getAuthor()                 
	{
		return author;
	}
	
	public String getDateOfPublication()
	{
		String publiDate = dateToString(dateOfPublication);
		return publiDate;
	}
	
	public int getPrice()
	{
		return price;
	}

	//--- helper methods -- DO NOT CHANGE ------------------------------------
	/** Converts the Date object d into a String object */
	public static String dateToString( Date d )
	{
		SimpleDateFormat fmt = new SimpleDateFormat( DATE_FORMAT );
		return fmt.format( d );
	}

	/** Converts the String object s into a Date object */
	public static Date stringToDate( String s ) 
	{
		Date r = null;
		try {
			SimpleDateFormat fmt = new SimpleDateFormat( DATE_FORMAT );
			r = fmt.parse( s );
		} catch ( ParseException e ){
			System.err.println( e );
			System.exit(1);
		}
		return r;
	}
}
