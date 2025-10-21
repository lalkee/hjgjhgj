/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.repository;

import bookstore.njtbookstore.domain.*;
import jakarta.persistence.*;
import jakarta.transaction.*;
import java.util.List;
import org.springframework.stereotype.*;

/**
 *
 * @author Milan
 */
@Repository
@Transactional
public class AuthorRepository implements RepositoryInterface<Author, Long> {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author findById(Long id) throws Exception {
        Author author = entityManager.find(Author.class, id);
        if (author == null) {
            throw new Exception("Author not found with id: " + id);
        }
        return author;
    }

    @Override
    public void save(Author entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        Author author = entityManager.find(Author.class, id);
        if (author != null) {
            entityManager.remove(author);
        }
    }
}
