/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.service;

import bookstore.njtbookstore.domain.Author;
import bookstore.njtbookstore.dto.AuthorDTO;
import bookstore.njtbookstore.mapper.AuthorMapper;
import bookstore.njtbookstore.repository.AuthorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    
    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper restaurantMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = restaurantMapper;
    }
    
    public List<AuthorDTO> findAll(){
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public AuthorDTO findById(Long id) throws Exception{
        return authorMapper.toDto(authorRepository.findById(id));
    }
    
    public AuthorDTO create(AuthorDTO dto){
        Author author = authorMapper.toEntity(dto);
        authorRepository.save(author);
        return authorMapper.toDto(author);
    }
    
    public void deleteById(Long id){
        authorRepository.deleteById(id);
    }
    
    public AuthorDTO update(AuthorDTO dto){
        Author updated = authorMapper.toEntity(dto);
        authorRepository.save(updated);
        return authorMapper.toDto(updated);
    }
}
