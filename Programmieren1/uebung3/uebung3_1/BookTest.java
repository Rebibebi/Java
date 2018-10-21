package uebung3_1;
//Rebecca Vogt (Hörerin), Matrikelnummer 03-054-889
//Programmierung 1, Universität Bern, HS 2018
//
//Übung 3-1


public class BookTest {

	public static void main(String[] args) {
	Book book1 = new Book();					//Book-Objekt wird instantiiert mit Standardwerten aus Konstruktor
	System.out.print(book1);			//Ausgabe des ersten Book-Objektes
	book1.input();								//Neue Werte für Book-Objekt von Nutzer erfragen und eingeben
	System.out.print(book1);
	book1.setId(2);
	System.out.println(book1.getId());
	book1.setTitle("Jurassic Park");
	System.out.println(book1.getTitle());
	book1.setAuthor("Michael Crichton");
	System.out.println(book1.getAuthor());
	book1.setDateOfPublication("1.2.1990");
	System.out.println(book1.getDateOfPublication());
	}

}
