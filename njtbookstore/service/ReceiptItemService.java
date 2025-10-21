/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.service;

import bookstore.njtbookstore.domain.ReceiptItem;
import bookstore.njtbookstore.dto.ReceiptItemDTO;
import bookstore.njtbookstore.mapper.ReceiptItemMapper;
import bookstore.njtbookstore.repository.ReceiptItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
public class ReceiptItemService {

    private final ReceiptItemRepository receiptItemRepository;
    private final ReceiptItemMapper receiptItemMapper;

    @Autowired
    public ReceiptItemService(ReceiptItemRepository receiptItemRepository, ReceiptItemMapper receiptItemMapper) {
        this.receiptItemRepository = receiptItemRepository;
        this.receiptItemMapper = receiptItemMapper;
    }

    public List<ReceiptItemDTO> findAll() {
        return receiptItemRepository.findAll()
                .stream()
                .map(receiptItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReceiptItemDTO findById(Long id) throws Exception {
        return receiptItemMapper.toDto(receiptItemRepository.findById(id));
    }

    public ReceiptItemDTO create(ReceiptItemDTO dto) {
        ReceiptItem item = receiptItemMapper.toEntity(dto);
        receiptItemRepository.save(item);
        return receiptItemMapper.toDto(item);
    }

    public ReceiptItemDTO update(ReceiptItemDTO dto) {
        ReceiptItem updated = receiptItemMapper.toEntity(dto);
        receiptItemRepository.save(updated);
        return receiptItemMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        receiptItemRepository.deleteById(id);
    }
}