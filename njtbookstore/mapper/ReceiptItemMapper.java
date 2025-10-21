/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.mapper;

import bookstore.njtbookstore.domain.ReceiptItem;
import bookstore.njtbookstore.dto.ReceiptItemDTO;
import org.springframework.stereotype.Component;
/**
 *
 * @author Milan
 */
@Component
public class ReceiptItemMapper implements DtoEntityMapper<ReceiptItemDTO, ReceiptItem> {

    private final BookMapper bookMapper;

    public ReceiptItemMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public ReceiptItemDTO toDto(ReceiptItem item) {
        if (item == null) return null;
        ReceiptItemDTO dto = new ReceiptItemDTO();
        dto.setId(item.getId());
        dto.setItemNo(item.getItemNo());
        dto.setPrice(item.getPrice());
        dto.setAmount(item.getAmount());
        dto.setBook(bookMapper.toDto(item.getBook()));
        return dto;
    }

    @Override
    public ReceiptItem toEntity(ReceiptItemDTO dto) {
        if (dto == null) return null;
        ReceiptItem item = new ReceiptItem();
        item.setId(dto.getId());
        item.setItemNo(dto.getItemNo());
        item.setPrice(dto.getPrice());
        item.setAmount(dto.getAmount());
        item.setBook(bookMapper.toEntity(dto.getBook()));
        return item;
    }
}
