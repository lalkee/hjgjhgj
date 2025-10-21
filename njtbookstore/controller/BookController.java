/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.controller;

import bookstore.njtbookstore.dto.BookDTO;
import bookstore.njtbookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
/**
 *
 * @author Milan
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Book entities.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = BookDTO.class), mediaType = "application/json")
    })
    public ResponseEntity<List<BookDTO>> getAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@NotNull @PathVariable Long id) {
        try {
            return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found: " + ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a new Book entity.")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO dto) {
        try {
            return new ResponseEntity<>(bookService.create(dto), HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create Book: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Book entity.")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO dto) {
        try {
            if (!id.equals(dto.getId())) {
                throw new IllegalArgumentException("Path ID and DTO ID must match");
            }
            return new ResponseEntity<>(bookService.update(dto), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update Book: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Book by ID.")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteById(id);
            return new ResponseEntity<>("Book successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to delete Book: " + ex.getMessage());
        }
    }
}