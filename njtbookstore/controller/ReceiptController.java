/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.controller;

import bookstore.njtbookstore.dto.ReceiptDTO;
import bookstore.njtbookstore.service.ReceiptService;
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
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Receipts.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReceiptDTO.class), mediaType = "application/json")
    })
    public ResponseEntity<List<ReceiptDTO>> getAll() {
        return new ResponseEntity<>(receiptService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptDTO> getById(@NotNull @PathVariable Long id) {
        try {
            return new ResponseEntity<>(receiptService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receipt not found: " + ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a new Receipt.")
    public ResponseEntity<ReceiptDTO> addReceipt(@Valid @RequestBody ReceiptDTO dto) {
        try {
            return new ResponseEntity<>(receiptService.create(dto), HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create Receipt: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Receipt.")
    public ResponseEntity<ReceiptDTO> updateReceipt(@PathVariable Long id, @Valid @RequestBody ReceiptDTO dto) {
        try {
            if (!id.equals(dto.getId())) {
                throw new IllegalArgumentException("Path ID and DTO ID must match");
            }
            return new ResponseEntity<>(receiptService.update(dto), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update Receipt: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Receipt by ID.")
    public ResponseEntity<String> deleteReceipt(@PathVariable Long id) {
        try {
            receiptService.deleteById(id);
            return new ResponseEntity<>("Receipt successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to delete Receipt: " + ex.getMessage());
        }
    }
}
