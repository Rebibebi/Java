package uebung3_1;
//Rebecca Vogt (Hörerin), Matrikelnummer 03-054-889
//Programmierung 1, Universität Bern, HS 2018
//
//Übung 3-1
//
//gittest
import java.util.Date;
import java.util.Scanner;
import java.text.*;

public class Book
{
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;
	private int ageBook;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	//--- constructors ---


	public Book( )
	{
		id = 0;
		title = "unknown";
		author = "unknown";
		dateOfPublication = stringToDate("01.01.1999");
		ageBook = age();

	}


	/** Returns the age of the book in days since publication */
	public int age()
	{
		Date datAktuell = new Date(); //aktuelles Systemdatum erzeugen
		long datDiff = datAktuell.getTime()-dateOfPublication.getTime(); //Differenz in Millisekunden von aktuellem Datum und Publikationsdatum
		long ageBooklong = datDiff / 86400000;
		ageBook = (int)ageBooklong; //Differenz wird von Milisekunden in Tage umgerechnet
		return ageBook; //int-Wert der Tage wird zurückgegeben
	}
		

	/** Returns a String representation of the book */
	public String toString()
	{
		String ausgabe = "ID: " + id + "\n" + "Titel: " + title + "\n" + "Autor: " + author + "\n" + "Publikationsdatum: " + dateToString(dateOfPublication) + "\n" + "Tage vergangen seit Publikation: " + ageBook + "\n";
		return ausgabe;
	}

	/** Reads all book data from user input */
	public void input() 
	{
		System.out.println( "Please enter id: " );
		Scanner scanner = new Scanner(System.in);
		setId(scanner.nextInt());
		scanner.nextLine();
		System.out.println( "Please enter booktitle: " );
		setTitle(scanner.nextLine());
		System.out.println( "Please enter author: " );
		setAuthor(scanner.nextLine());
		System.out.println( "Please enter date of publication (dd.mm.yyyy): " );
		setDateOfPublication(scanner.nextLine());
		age();
		scanner.close();
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
