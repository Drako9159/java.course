package drako.library.service;

import drako.library.model.Book;

import java.util.List;

public interface IBookService {
    public List<Book> listBooks();

    public Book findBookById(Integer idBook);

    public void addBook(Book book);

    public void deleteBook(Book book);
}
