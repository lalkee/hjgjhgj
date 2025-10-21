/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.service;

import bookstore.njtbookstore.domain.Receipt;
import bookstore.njtbookstore.dto.ReceiptDTO;
import bookstore.njtbookstore.mapper.ReceiptMapper;
import bookstore.njtbookstore.repository.ReceiptRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository, ReceiptMapper receiptMapper) {
        this.receiptRepository = receiptRepository;
        this.receiptMapper = receiptMapper;
    }

    public List<ReceiptDTO> findAll() {
        return receiptRepository.findAll()
                .stream()
                .map(receiptMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReceiptDTO findById(Long id) throws Exception {
        return receiptMapper.toDto(receiptRepository.findById(id));
    }

//    public ReceiptDTO create(ReceiptDTO dto) {
//        Receipt receipt = receiptMapper.toEntity(dto);
//        receiptRepository.save(receipt);
//        return receiptMapper.toDto(receipt);
//    }
    
    public ReceiptDTO create(ReceiptDTO dto) {
    Receipt receipt = receiptMapper.toEntity(dto);

    // Set timestamp automatically
    receipt.setTimestamp(java.time.LocalDateTime.now());

    // Save
    receiptRepository.save(receipt);
    return receiptMapper.toDto(receipt);
}

    public ReceiptDTO update(ReceiptDTO dto) {
        Receipt updated = receiptMapper.toEntity(dto);
        receiptRepository.save(updated);
        return receiptMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        receiptRepository.deleteById(id);
    }
}
