/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.controller;

import bookstore.njtbookstore.dto.AuthorDTO;
import bookstore.njtbookstore.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Milan
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    @Operation(summary = "Retrieve all Author entities.")
    @ApiResponse(responseCode = "200", content={
        @Content(schema = @Schema(implementation = AuthorDTO.class),
            mediaType = "application/json")
    })
    public ResponseEntity<List<AuthorDTO>> getAll(){
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getById(
            @NotNull(message = "Should not be null or emtpy.")
            @PathVariable(value = "id") Long id){
        try {
            return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "AuthorController exception: "+ex.getMessage());
        }
    }
    
    @PostMapping
    @Operation(summary = "Create a new Author entity.")
    @ApiResponse(responseCode = "201", content={
        @Content(schema = @Schema(implementation = AuthorDTO.class),
            mediaType = "application/json")
    })
    public ResponseEntity<AuthorDTO> addAuthor(@Valid @RequestBody AuthorDTO dto) {
        try {
            AuthorDTO created = authorService.create(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Failed to create Author: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Author entity.")
    @ApiResponse(responseCode = "200", content={
        @Content(schema = @Schema(implementation = AuthorDTO.class),
            mediaType = "application/json")
    })
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody AuthorDTO dto) {
        try {
            AuthorDTO updated = authorService.update(dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Failed to update Author: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Author by ID.")
    public ResponseEntity<Void> deleteAuthor(
            @NotNull(message = "ID should not be null.")
            @PathVariable("id") Long id) {
        try {
            authorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Failed to delete Author: " + ex.getMessage());
        }
    }
}
