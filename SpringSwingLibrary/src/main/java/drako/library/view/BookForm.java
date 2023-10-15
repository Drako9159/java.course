package drako.library.view;

import drako.library.model.Book;
import drako.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


@Component
public class BookForm extends JFrame {

    BookService bookService;
    private JPanel panel;
    private JTable tableBook;
    private JTextField idText;
    private JTextField bookText;
    private JTextField authorText;
    private JTextField priceText;
    private JTextField stockText;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private DefaultTableModel tableModelBook;

    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;
        runForm();
        addButton.addActionListener(e -> addLibrary());
        tableBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadLibrarySelected();
            }
        });
        modifyButton.addActionListener(e -> modifyBook());
        deleteButton.addActionListener(e -> deleteBook());
    }

    private void deleteBook() {
        var line = tableBook.getSelectedRow();
        if (line != -1) {
            String idBook = tableBook.getModel().getValueAt(line, 0).toString();
            var book = new Book();
            book.setIdBook(Integer.parseInt(idBook));
            bookService.deleteBook(book);
            showMessage("Book " + idBook + " deleted...");
            cleanForm();
            listBooks();
        } else {
            showMessage("Please select an book to delete");
        }
    }

    private void modifyBook() {
        if (this.idText.getText().equals("")) {
            showMessage("Please select data");
        } else {
            if (bookText.getText().equals("")) {
                showMessage("Input book name");
                bookText.requestFocusInWindow();
                return;
            }
            int idBook = Integer.parseInt(idText.getText());
            var bookName = bookText.getText();
            var author = authorText.getText();
            var price = Double.parseDouble(priceText.getText());
            var stock = Integer.parseInt(stockText.getText());
            var book = new Book(idBook, bookName, author, price, stock);
            bookService.addBook(book);
            showMessage("Library is modified...");
            cleanForm();
            listBooks();
        }
    }

    private void loadLibrarySelected() {
        var line = tableBook.getSelectedRow();
        if (line != -1) {
            String idBook = tableBook.getModel().getValueAt(line, 0).toString();
            idText.setText(idBook);
            String nameBook = tableBook.getModel().getValueAt(line, 1).toString();
            bookText.setText(nameBook);
            String author = tableBook.getModel().getValueAt(line, 2).toString();
            authorText.setText(author);
            String price = tableBook.getModel().getValueAt(line, 3).toString();
            priceText.setText(price);
            String stock = tableBook.getModel().getValueAt(line, 4).toString();
            stockText.setText(stock);
        }
    }

    ;

    private void runForm() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth() / 2);
        int y = (screenSize.height - getHeight() / 2);
        setLocation(x, y);

    }

    private void addLibrary() {
        if (bookText.getText().equals("")) {
            showMessage("Input book name");
            bookText.requestFocusInWindow();
            return;
        }
        var nameBook = bookText.getText();
        var author = authorText.getText();
        var price = Double.parseDouble(priceText.getText());
        var stock = Integer.parseInt(stockText.getText());

        var book = new Book(null, nameBook, author, price, stock);
        this.bookService.addBook(book);
        showMessage("Library is added...");
        cleanForm();
        listBooks();
    }

    private void cleanForm() {
        bookText.setText("");
        authorText.setText("");
        priceText.setText("");
        stockText.setText("");

    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void createUIComponents() {
        idText = new JTextField("");
        idText.setVisible(false);

        this.tableModelBook = new DefaultTableModel(0, 5) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] headers = {"Id", "Name", "Author", "Price", "Stock"};
        tableModelBook.setColumnIdentifiers(headers);
        this.tableBook = new JTable(tableModelBook);

        tableBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listBooks();

    }

    private void listBooks() {
        tableModelBook.setRowCount(0);
        var books = bookService.listBooks();
        books.forEach((book) -> {
            Object[] lineBook = {book.getIdBook(), book.getNameBook(), book.getAuthor(), book.getPrice(), book.getStock()};
            this.tableModelBook.addRow(lineBook);
        });

    }
}
