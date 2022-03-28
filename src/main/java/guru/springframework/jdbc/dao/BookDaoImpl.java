package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Book;
import guru.springframework.jdbc.repositories.BookRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component public class BookDaoImpl implements BookDao {

    public static final String BOOK_WITH_TITLE_NOT_FOUND = "Book with title \"%s\" not found";
    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title).orElseThrow(() -> new EntityNotFoundException(String.format(BOOK_WITH_TITLE_NOT_FOUND, title)));
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        Book found = bookRepository.getById(book.getId());
        found.setTitle(book.getTitle());
        found.setIsbn(book.getIsbn());
        found.setPublisher(book.getPublisher());
        found.setAuthorId(book.getAuthorId());
        return bookRepository.save(found);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override public List<Book> findAllBooks(int size, int offset) {
        return null;
    }

    @Override public List<Book> findAllBooks() {
        return null;
    }
}

