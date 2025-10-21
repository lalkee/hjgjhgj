/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.service;

import bookstore.njtbookstore.domain.Book;
import bookstore.njtbookstore.dto.BookDTO;
import bookstore.njtbookstore.mapper.BookMapper;
import bookstore.njtbookstore.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO findById(Long id) throws Exception {
        return bookMapper.toDto(bookRepository.findById(id));
    }

    public BookDTO create(BookDTO dto) {
        Book book = bookMapper.toEntity(dto);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDTO update(BookDTO dto) {
        Book updated = bookMapper.toEntity(dto);
        bookRepository.save(updated);
        return bookMapper.toDto(updated);
    }
}