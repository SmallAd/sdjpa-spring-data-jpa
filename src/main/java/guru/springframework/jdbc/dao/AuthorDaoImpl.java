package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.repositories.AuthorRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 8/28/21.
 */
@Component
public class AuthorDaoImpl implements AuthorDao {

    public static final String AUTHOR_NOT_FOUND = "Author with first name '%s' and last name '%s'  not found";
    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable) {
        return null;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new EntityNotFoundException(String.format(AUTHOR_NOT_FOUND, firstName, lastName)));
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Author fetched = authorRepository.getById(author.getId());
        fetched.setFirstName(author.getFirstName());
        fetched.setLastName(author.getLastName());
        return authorRepository.save(fetched);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
