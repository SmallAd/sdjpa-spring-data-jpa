package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class AuthorDaoHibernate implements AuthorDao{

    private final EntityManagerFactory emf;

    public AuthorDaoHibernate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable) {
        EntityManager em = getEntityManager();
        try {

            StringBuilder hql = new StringBuilder("SELECT a FROM Author a WHERE a.lastName = :last_name");
            Optional.ofNullable(pageable.getSort().getOrderFor("firstname"))
                    .map(Sort.Order::getDirection)
                    .map(Sort.Direction::name)
                    .ifPresent(direction -> hql.append(" ORDER BY a.firstName ").append(direction));

            TypedQuery<Author> query = em.createQuery( hql.toString(), Author.class)
                    .setParameter("last_name", lastName)
                    .setMaxResults(pageable.getPageSize())
                    .setFirstResult(Math.toIntExact(pageable.getOffset()));

            return query.getResultList();
        } finally {
            em.close();
        }
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

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
