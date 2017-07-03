package stmarco.library.ui;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import stmarco.library.data.FileDataSource;
import stmarco.library.model.Book;

public class LibraryFormMain implements ActionListener {

	private JFrame frame;
	private JTable table;
	private JTextArea descriptionArea;
	private JButton addBtn;
	private JButton editBtn;
	private JButton deleteBtn;
	private JButton closeBtn;
	private JScrollPane descriptionScrollPane;
	private int selectedBookID = 0;
	private DefaultTableModel model;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryFormMain window = new LibraryFormMain();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LibraryFormMain() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setResizable(false);
		frame.setTitle("Home Library Management System");
		frame.setBounds(100, 100, 821, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JScrollPane tableScroll = new JScrollPane();
		tableScroll.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		tableScroll.setFocusable(false);
		tableScroll.setBounds(10, 11, 624, 419);
		frame.getContentPane().add(tableScroll);

		String[] header = { "#", "Title", "Writer", "Genre", "Publication Year" };

		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);

		populateTable();

		table = new JTable(model);
		table.setShowVerticalLines(false);
		table.setFocusable(false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				int selectedRow = table.getSelectedRow();
				selectedBookID = selectedRow + 1;
				if (selectedRow >= 0) {
					table.setRowSelectionInterval(selectedRow, selectedRow);
					table.setRowSelectionAllowed(true);
					descriptionArea.setText(FileDataSource.getInstance().getAllBooks().get(selectedRow).getDescription());
					editBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
				} else {
					descriptionArea.setText("");
					editBtn.setEnabled(false);
					deleteBtn.setEnabled(false);
				}
			}
		});
		tableScroll.setViewportView(table);
		table.getColumn("#").setMaxWidth(50);
		table.getColumn("Title").setPreferredWidth(150);
		table.getColumn("Writer").setPreferredWidth(150);
		table.getColumn("Genre").setPreferredWidth(120);
		table.getTableHeader().setReorderingAllowed(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i <= 4; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		addBtn = new JButton("Add Book");
		addBtn.addActionListener(this);
		addBtn.setActionCommand("add_btn");
		addBtn.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		addBtn.setFocusable(false);
		addBtn.setBackground(SystemColor.controlHighlight);
		addBtn.setBounds(647, 11, 157, 35);
		frame.getContentPane().add(addBtn);

		editBtn = new JButton("Edit Book");
		editBtn.addActionListener(this);
		editBtn.setActionCommand("edit_btn");
		editBtn.setEnabled(false);
		editBtn.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		editBtn.setBackground(SystemColor.controlHighlight);
		editBtn.setFocusable(false);
		editBtn.setBounds(647, 57, 157, 35);
		frame.getContentPane().add(editBtn);

		deleteBtn = new JButton("Delete Book");
		deleteBtn.addActionListener(this);
		deleteBtn.setActionCommand("delete_btn");
		deleteBtn.setEnabled(false);
		deleteBtn.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		deleteBtn.setBackground(SystemColor.controlHighlight);
		deleteBtn.setFocusable(false);
		deleteBtn.setBounds(647, 103, 157, 35);
		frame.getContentPane().add(deleteBtn);

		closeBtn = new JButton("Close");
		closeBtn.addActionListener(this);
		closeBtn.setActionCommand("close_btn");
		closeBtn.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
		closeBtn.setBackground(SystemColor.controlHighlight);
		closeBtn.setFocusable(false);
		closeBtn.setBounds(647, 395, 157, 35);
		frame.getContentPane().add(closeBtn);

		descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBorder(null);
		descriptionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descriptionScrollPane.setBounds(647, 149, 157, 229);
		frame.getContentPane().add(descriptionScrollPane);

		descriptionArea = new JTextArea();
		descriptionScrollPane.setViewportView(descriptionArea);
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setFocusable(false);
		descriptionArea.setEditable(false);
		descriptionArea.setBorder(new LineBorder(SystemColor.textHighlight, 1, true));
	}

	public void populateTable() {
		model.setRowCount(0);
		for (Book book : FileDataSource.getInstance().getAllBooks()) {
			model.addRow(new Object[] { book.getBookID(), book.getTitle(), book.getWriter(), book.getGenre(),
					book.getPublicationYear(), book.getDescription() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		String action = arg.getActionCommand();		
		if (action.equals("add_btn")) { 												// ADD Button action
			new AddEditBookFrame(this, AddEditBookFrame.State.ADD, new Book());
		} else if (action.equals("edit_btn")) { 										// EDIT Button action
			Book book = FileDataSource.getInstance().getBookByID(selectedBookID);
			new AddEditBookFrame(this, AddEditBookFrame.State.EDIT, book);
		} else if (action.equals("delete_btn")) { 										// DELETE Button action
			Book book = FileDataSource.getInstance().getBookByID(selectedBookID);
			FileDataSource.getInstance().deleteBook(book);
			populateTable();
		} else if (action.equals("close_btn")) {
			System.exit(0);
		}
	}
}