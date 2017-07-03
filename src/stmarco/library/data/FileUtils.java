package stmarco.library.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import stmarco.library.model.Book;

public class FileUtils {

	private final static String SEPARATOR = ":::";
	private final static String DB_FILE = "BooksData.txt";

	
	public static void save2file(ArrayList<Book> bookList) {
		File file = new File(DB_FILE);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
			for (Book book : bookList) {
				String line = book.getBookID() + SEPARATOR + book.getTitle() + SEPARATOR + book.getWriter() + SEPARATOR
						+ book.getGenre() + SEPARATOR + book.getPublicationYear() + SEPARATOR + " " + book.getDescription();
				bw.write(line);
				bw.newLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: file not found " + DB_FILE + ". " + e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<Book> readFromFile() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		File file = new File(DB_FILE);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				Book bk = new Book();

				String[] arr = line.split(SEPARATOR);
				int ID = Integer.parseInt(arr[0]);

				bk.setBookID(ID);
				bk.setTitle(arr[1]);
				bk.setWriter(arr[2]);
				bk.setGenre(arr[3]);
				bk.setPublicationYear(arr[4]);
				bk.setDescription(arr[5]);

				bookList.add(bk);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: file not found " + DB_FILE + ". " + e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return bookList;
	}
}