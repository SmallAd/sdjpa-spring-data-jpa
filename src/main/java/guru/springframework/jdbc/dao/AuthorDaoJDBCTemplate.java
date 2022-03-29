package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoJDBCTemplate implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM author WHERE last_name = ?");

        Optional.of(pageable.getSort())
                .map(s -> s.getOrderFor("firstname"))
                .map(Sort.Order::getDirection)
                .map(Sort.Direction::name)
                .ifPresent(direction -> sql.append(" ORDER BY first_name ").append(direction));

        sql.append(" limit ? offset ?");

        return jdbcTemplate.query(sql.toString(), getAuthorMapper(),
                lastName, pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }

    private AuthorMapper getAuthorMapper() {
        return new AuthorMapper();
    }
}
