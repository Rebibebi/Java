package uebung3_2;

import java.util.*;
import java.text.*;

public class Order {

// Objektvariablen
	
	private int id;
	private String customerName;
	private String customerAddress;
	static int orderIndex = 0;			// statischer Index für Order-Objekte
	static private int BUCHMAX;			// Maximale Anzahl Buchobjekte pro Order-Objekt
	private ArrayList<Book> buchMenge = new ArrayList<Book>();
	

	public Order()
	{
		
		
	}
	
// Ausgabe eines Order-Objektes als String
	
	public String toString() 
	{
		String ausgabe = "Order id: " + id + ", Customer: " + customerName + ", " + customerAddress + "\n" + getBookString();
		return ausgabe;
	}
	
// Book-Objekte werden hinzugefügt
	
	public void addBook(Book a)
	{
		if (buchMenge.size() <= BUCHMAX)  //Bookobjekte nur hinzufügen, sofern weniger als 5 bereits enthalten
		{
			buchMenge.add(a);	
		}
	}
	
// Setter-Methoden für Order-Objekte
	
	public void setCustomerName(String variable1)
	{
		customerName = variable1;
	}
	
	public void setCustomerAddress(String variable2)
	{
		customerAddress = variable2;
	}
	
// Getter-Methode für Book-Objekte
	
	public String getBookString() 
	{
		String buecher = "";
		for (Book b: buchMenge)
		//while (count <= buchMenge.size())  //Bookobjekte so lange ausgeben, bis alle abgearbeitet sind
		{
		buecher = buecher + b.toString();
		
		}
		return buecher;
	}
	
}
