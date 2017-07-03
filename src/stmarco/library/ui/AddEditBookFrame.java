package stmarco.library.ui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import stmarco.library.data.FileDataSource;
import stmarco.library.model.Book;

@SuppressWarnings("serial")
public class AddEditBookFrame extends JDialog implements ActionListener {

	public enum State {
		ADD, EDIT
	}

	private JPanel contentPane;
	private JTextField txtBookId;
	private JTextField bookID;
	private JTextField txtTitle;
	private JTextField title;
	private JTextField txtWriter;
	private JTextField writer;
	private JTextField txtPublicationYear;
	private JTextField publicationYear;
	private JButton saveBtn;
	private JButton cancelBtn;
	private JTextField txtDescription;
	private JComboBox<String> genre;
	private JTextArea description;
	private JScrollPane scrollPane;
	private State state;
	private Book book;
	private LibraryFormMain parent;

	
	public AddEditBookFrame(LibraryFormMain parent, State state, Book book) {
		this.parent = parent;
		this.state = state;
		this.book = book;

		setResizable(false);
		setTitle("Edit Book");
		setBounds(100, 100, 450, 273);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 434, 261);
		getContentPane().add(contentPane);

		txtBookId = new JTextField();
		txtBookId.setBounds(10, 11, 60, 35);
		txtBookId.setFocusable(false);
		txtBookId.setBackground(SystemColor.control);
		txtBookId.setBorder(null);
		txtBookId.setEditable(false);
		txtBookId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookId.setText("Book ID");
		contentPane.add(txtBookId);
		txtBookId.setColumns(10);

		bookID = new JTextField();
		bookID.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		bookID.setBounds(80, 11, 45, 35);
		bookID.setHorizontalAlignment(SwingConstants.CENTER);
		bookID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bookID.setEditable(false);
		bookID.setFocusable(false);
		contentPane.add(bookID);
		bookID.setColumns(10);

		title = new JTextField();
		title.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		title.setBounds(80, 57, 140, 35);
		title.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(title);
		title.setColumns(10);

		txtTitle = new JTextField();
		txtTitle.setBounds(10, 58, 78, 35);
		txtTitle.setFocusable(false);
		txtTitle.setEditable(false);
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setBackground(SystemColor.control);
		txtTitle.setBorder(null);
		txtTitle.setText("Title");
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);

		writer = new JTextField();
		writer.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		writer.setBounds(80, 103, 140, 35);
		writer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(writer);
		writer.setColumns(10);

		txtWriter = new JTextField();
		txtWriter.setBounds(10, 103, 78, 35);
		txtWriter.setFocusable(false);
		txtWriter.setEditable(false);
		txtWriter.setBorder(null);
		txtWriter.setBackground(SystemColor.control);
		txtWriter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtWriter.setText("Writer");
		contentPane.add(txtWriter);
		txtWriter.setColumns(10);

		publicationYear = new JTextField();
		publicationYear.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		publicationYear.setBounds(124, 197, 96, 35);
		publicationYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		publicationYear.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(publicationYear);
		publicationYear.setColumns(10);

		txtPublicationYear = new JTextField();
		txtPublicationYear.setBounds(10, 197, 138, 35);
		txtPublicationYear.setFocusable(false);
		txtPublicationYear.setEditable(false);
		txtPublicationYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPublicationYear.setBorder(null);
		txtPublicationYear.setBackground(SystemColor.control);
		txtPublicationYear.setText("Publication Year");
		contentPane.add(txtPublicationYear);
		txtPublicationYear.setColumns(10);

		String[] genres = { "Genres", " ", "Science fiction", "Action and Adventure", "Mystery", "History", "Cookbooks",
				"..." };

		genre = new JComboBox<String>(genres);
		genre.setBackground(SystemColor.text);
		genre.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		genre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		genre.setBounds(10, 149, 210, 37);
		contentPane.add(genre);

		saveBtn = new JButton("Save");
		saveBtn.addActionListener(this);
		saveBtn.setActionCommand("save_btn");
		saveBtn.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		saveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveBtn.setFocusable(false);
		saveBtn.setBackground(SystemColor.controlHighlight);
		saveBtn.setBounds(232, 197, 96, 35);
		contentPane.add(saveBtn);

		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(this);
		cancelBtn.setActionCommand("cancel_btn");
		cancelBtn.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelBtn.setFocusable(false);
		cancelBtn.setBackground(SystemColor.controlHighlight);
		cancelBtn.setBounds(338, 197, 96, 35);
		contentPane.add(cancelBtn);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(232, 18, 202, 166);
		contentPane.add(scrollPane);

		description = new JTextArea();
		scrollPane.setViewportView(description);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));

		txtDescription = new JTextField();
		txtDescription.setFocusable(false);
		txtDescription.setEditable(false);
		txtDescription.setBorder(null);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDescription.setBackground(SystemColor.control);
		txtDescription.setText("Description");
		txtDescription.setBounds(147, 11, 110, 35);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);

		setFrame();
	}

	private void setData(Book book) {
		bookID.setText(Integer.toString(book.getBookID()));
		title.setText(book.getTitle());
		writer.setText(book.getWriter());
		publicationYear.setText(book.getPublicationYear());
		description.setText(book.getDescription());

		if (book.getGenre().equalsIgnoreCase("science fiction")) {
			genre.setSelectedIndex(2);
		} else if (book.getGenre().equalsIgnoreCase("action and adventure")) {
			genre.setSelectedIndex(3);
		} else if (book.getGenre().equalsIgnoreCase("mystery")) {
			genre.setSelectedIndex(4);
		} else if (book.getGenre().equalsIgnoreCase("history")) {
			genre.setSelectedIndex(5);
		} else if (book.getGenre().equalsIgnoreCase("cookbooks")) {
			genre.setSelectedIndex(6);
		} else {
			genre.setSelectedIndex(0);
		}
	}

	private void setFrame() {
		if (state == State.ADD) {
			String ID = Integer.toString(FileDataSource.getInstance().getNextID());
			bookID.setText(ID);
			setTitle("Add Book");
		} else if (state == State.EDIT) {
			setTitle("Edit Book");
			setData(book);
		}
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		String action = arg.getActionCommand();

		if (action.equals("save_btn")) {
			if (state == State.ADD) {
				int ID = FileDataSource.getInstance().getNextID();
				Book book = new Book(ID, title.getText(), writer.getText(), genre.getSelectedItem().toString(),
						publicationYear.getText(), description.getText());

				FileDataSource.getInstance().saveNewBook(book);
				parent.populateTable();
				dispose();
			} else if (state == State.EDIT) {
				book.setTitle(title.getText());
				book.setWriter(writer.getText());
				book.setGenre(genre.getSelectedItem().toString());
				book.setPublicationYear(publicationYear.getText());
				book.setDescription(description.getText());

				FileDataSource.getInstance().updateBook(book);
				parent.populateTable();
				dispose();
			}
		} else if (action.equals("cancel_btn")) {
			dispose();
		}
	}
}