package uebung3_2;

import java.util.*;

public class Order {

// Objektvariablen
	
	private static int ID;
	private String customerName;
	private String customerAddress;
	private static int BUCHMAX = 5;			// Maximale Anzahl Buchobjekte pro Order-Objekt
	private ArrayList<Book> buchMenge = new ArrayList<Book>();

//Statische Variable, die jedem Book-Objekt eine fortlaufende ID zuweist
	public Order()
	{
		ID++;
	}
	
// Ausgabe eines Order-Objektes als String
	
	public String toString() 
	{
		String ausgabe = "Order id: " + ID + ", Customer: " + customerName + ", " + customerAddress + getBookString() + "\n" + "Total price: " + getTotalPrice() + " CHF";
		return ausgabe;
	}
	
// Book-Objekte werden hinzugefügt
	
	public void addBook(Book a)
	{
		if (buchMenge.size() < BUCHMAX)  //Bookobjekte nur hinzufügen, sofern weniger als 5 bereits enthalten
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
		{											//Bookobjekte so lange ausgeben, bis alle abgearbeitet sind
		buecher = buecher + "\n" + b.toString();
		}
		
		return buecher;
	}
	
// Getter-Methode für Preistotal
	
	public int getTotalPrice()
	{
		int preisTotal = 0;
		for (Book b: buchMenge)
		{											//Bookobjekte so lange ausgeben, bis alle abgearbeitet sind
			preisTotal = preisTotal + b.getPrice();
		}
		
		return preisTotal;	
	}
	
}
