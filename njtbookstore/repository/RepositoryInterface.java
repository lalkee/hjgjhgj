/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.repository;

import java.util.List;

/**
 *
 * @author Milan
 */
public interface RepositoryInterface<E, Id> {
    List<E> findAll();
    E findById(Id id) throws Exception;
    void save(E entity);
    void deleteById(Id id);
}
