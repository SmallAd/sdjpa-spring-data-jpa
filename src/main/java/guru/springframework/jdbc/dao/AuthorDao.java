package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * Created by jt on 8/22/21.
 */
public interface AuthorDao {

    List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable);

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
