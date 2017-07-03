package stmarco.library.model;

public class Book {

	private String title;
	private String writer;
	private String publicationYear;
	private String genre;
	private String description;
	private int bookID;

	
	public Book() {
	}

	public Book(int id, String title, String writer, String genre, String publicationYear, String description) {
		super();
		this.bookID = id;
		this.title = title;
		this.writer = writer;
		this.genre = genre;
		this.publicationYear = publicationYear;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String year) {
		this.publicationYear = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	@Override
	public String toString() {
		return bookID + " " + title + " " + writer + " " + genre + " " + " " + publicationYear + " " + description;
	}
}
