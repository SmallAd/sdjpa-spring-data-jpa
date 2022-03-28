package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookDao {

    List<Book> findAllBooks(Pageable pageable);

    List<Book> findAllBooks(int size, int offset);

    List<Book> findAllBooks();

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}
