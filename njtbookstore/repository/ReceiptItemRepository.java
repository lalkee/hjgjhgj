/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.repository;

import bookstore.njtbookstore.domain.ReceiptItem;
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
public class ReceiptItemRepository implements RepositoryInterface<ReceiptItem, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ReceiptItem> findAll() {
        return em.createQuery("SELECT ri FROM ReceiptItem ri", ReceiptItem.class).getResultList();
    }

    @Override
    public ReceiptItem findById(Long id) throws Exception {
        ReceiptItem item = em.find(ReceiptItem.class, id);
        if (item == null) throw new Exception("ReceiptItem not found");
        return item;
    }

    @Override
    public void save(ReceiptItem entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        ReceiptItem item = em.find(ReceiptItem.class, id);
        if (item != null) {
            em.remove(item);
        }
    }
}
