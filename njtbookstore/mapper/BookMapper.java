/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.mapper;

import bookstore.njtbookstore.domain.Book;
import bookstore.njtbookstore.dto.BookDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class BookMapper implements DtoEntityMapper<BookDTO, Book>{

    private final AuthorMapper authorMapper = new AuthorMapper();
    
    @Override
    public BookDTO toDto(Book book) {
        if (book == null) return null;

        return new BookDTO(
            book.getId(),
            book.getTitle(),
            book.getIsbn(),
            book.getPrice(),
            authorMapper.toDto(book.getAuthor())
        );    
    }

    @Override
    public Book toEntity(BookDTO dto) {
        if (dto == null) return null;

        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setAuthor(authorMapper.toEntity(dto.getAuthor()));
        return book;
    }        
    
    
}
