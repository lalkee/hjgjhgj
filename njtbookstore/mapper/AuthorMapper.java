/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.mapper;

import bookstore.njtbookstore.domain.*;
import bookstore.njtbookstore.dto.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class AuthorMapper implements DtoEntityMapper<AuthorDTO, Author> {
    
    @Override
    public AuthorDTO toDto(Author author) {
        if (author == null) return null;

        return new AuthorDTO(
            author.getId(),
            author.getFirstName(),
            author.getLastName(),
            author.getDateOfBirth()
        );
    }

    @Override
    public Author toEntity(AuthorDTO dto) {
        if (dto == null) return null;

        Author author = new Author();
        author.setId(dto.getId());
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setDateOfBirth(dto.getDateOfBirth());
        return author;
    }    

}
