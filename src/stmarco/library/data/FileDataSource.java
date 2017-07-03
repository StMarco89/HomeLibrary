package stmarco.library.data;

import java.util.ArrayList;

import stmarco.library.model.Book;

public class FileDataSource {

	private ArrayList<Book> bookList;
	private static FileDataSource fileDataSource = new FileDataSource();

	
	private FileDataSource() {
		bookList = new ArrayList<Book>();
		bookList = FileUtils.readFromFile();
	}

	public static FileDataSource getInstance() {
		return fileDataSource;
	}

	public ArrayList<Book> getAllBooks() {
		return bookList;
	}

	public void saveNewBook(Book book) {
		bookList.add(book);
		FileUtils.save2file(bookList);
	}

	public void updateBook(Book book) {
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getBookID() == book.getBookID()) {
				bookList.remove(i);
				bookList.add(i, book);
				fixBooksID();
				FileUtils.save2file(bookList);
			}
		}
	}

	public void deleteBook(Book book) {
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getBookID() == book.getBookID()) {
				bookList.remove(i);
				fixBooksID();
				FileUtils.save2file(bookList);
			}
		}
	}

	public Book getBookByID(int id) {
		for (Book book : bookList) {
			if (book.getBookID() == id)
				return book;
		}
		return null;
	}

	public int getNextID() {
		return bookList.size() + 1;
	}

	private void fixBooksID() {
		for (int i = 0; i < bookList.size(); i++) {
			bookList.get(i).setBookID(i + 1);
		}
	}
}
