/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.repository;

import bookstore.njtbookstore.domain.Receipt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Milan
 */
@Repository
@Transactional
public class ReceiptRepository implements RepositoryInterface<Receipt, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Receipt> findAll() {
        return em.createQuery("SELECT r FROM Receipt r", Receipt.class).getResultList();
    }

    @Override
    public Receipt findById(Long id) throws Exception {
        Receipt receipt = em.find(Receipt.class, id);
        if (receipt == null) throw new Exception("Receipt not found");
        return receipt;
    }

    @Override
    public void save(Receipt entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        Receipt receipt = em.find(Receipt.class, id);
        if (receipt != null) {
            em.remove(receipt);
        }
    }
}