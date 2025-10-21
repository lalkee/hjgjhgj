/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.controller;

import bookstore.njtbookstore.dto.ReceiptItemDTO;
import bookstore.njtbookstore.service.ReceiptItemService;
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
@RequestMapping("/api/receipt-items")
public class ReceiptItemController {

    private final ReceiptItemService receiptItemService;

    public ReceiptItemController(ReceiptItemService receiptItemService) {
        this.receiptItemService = receiptItemService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all ReceiptItems.")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ReceiptItemDTO.class), mediaType = "application/json")
    })
    public ResponseEntity<List<ReceiptItemDTO>> getAll() {
        return new ResponseEntity<>(receiptItemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptItemDTO> getById(@NotNull @PathVariable Long id) {
        try {
            return new ResponseEntity<>(receiptItemService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ReceiptItem not found: " + ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a new ReceiptItem.")
    public ResponseEntity<ReceiptItemDTO> addReceiptItem(@Valid @RequestBody ReceiptItemDTO dto) {
        try {
            return new ResponseEntity<>(receiptItemService.create(dto), HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create ReceiptItem: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing ReceiptItem.")
    public ResponseEntity<ReceiptItemDTO> updateReceiptItem(@PathVariable Long id, @Valid @RequestBody ReceiptItemDTO dto) {
        try {
            if (!id.equals(dto.getId())) {
                throw new IllegalArgumentException("Path ID and DTO ID must match");
            }
            return new ResponseEntity<>(receiptItemService.update(dto), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update ReceiptItem: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a ReceiptItem by ID.")
    public ResponseEntity<String> deleteReceiptItem(@PathVariable Long id) {
        try {
            receiptItemService.deleteById(id);
            return new ResponseEntity<>("ReceiptItem successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to delete ReceiptItem: " + ex.getMessage());
        }
    }
}
