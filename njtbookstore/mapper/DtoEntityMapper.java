/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bookstore.njtbookstore.mapper;

/**
 *
 * @author Milan
 */
public interface DtoEntityMapper<T, E> {
    T toDto(E e);
    E toEntity(T t);
}
