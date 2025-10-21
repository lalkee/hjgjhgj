package bookstore.njtbookstore.mapper;

import bookstore.njtbookstore.domain.Receipt;
import bookstore.njtbookstore.domain.User;
import bookstore.njtbookstore.dto.ReceiptDTO;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class ReceiptMapper implements DtoEntityMapper<ReceiptDTO, Receipt> {

    private final ReceiptItemMapper receiptItemMapper;
    private final UserMapper userMapper;

    public ReceiptMapper(ReceiptItemMapper receiptItemMapper, UserMapper userMapper) {
        this.receiptItemMapper = receiptItemMapper;
        this.userMapper = userMapper;
    }

    @Override
    public ReceiptDTO toDto(Receipt receipt) {
        if (receipt == null) return null;

        ReceiptDTO dto = new ReceiptDTO();
        dto.setId(receipt.getId());
        dto.setTotalPrice(receipt.getTotalPrice());
        dto.setTimestamp(receipt.getTimestamp());
        dto.setAddress(receipt.getAddress());

        if (receipt.getUser() != null) {
            dto.setUser(userMapper.toDto(receipt.getUser()));
        }

        if (receipt.getReceiptItems() != null) {
            dto.setReceiptItems(
                receipt.getReceiptItems()
                       .stream()
                       .map(receiptItemMapper::toDto)
                       .collect(Collectors.toList())
            );
        }

        return dto;
    }

    @Override
    public Receipt toEntity(ReceiptDTO dto) {
        if (dto == null) return null;

        Receipt receipt = new Receipt();
        receipt.setId(dto.getId());
        receipt.setTotalPrice(dto.getTotalPrice());
        receipt.setTimestamp(dto.getTimestamp());
        receipt.setAddress(dto.getAddress());

        if (dto.getUser() != null) {
            User user = userMapper.toEntity(dto.getUser());
            receipt.setUser(user);
        }

        if (dto.getReceiptItems() != null) {
            receipt.setReceiptItems(
                dto.getReceiptItems()
                   .stream()
                   .map(receiptItemMapper::toEntity)
                   .peek(item -> item.setReceipt(receipt))
                   .collect(Collectors.toList())
            );
        }

        return receipt;
    }
}
