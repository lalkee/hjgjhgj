package bookstore.njtbookstore.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReceiptDTO implements DtoMarker {
    private Long id;
    private double totalPrice;
    private LocalDateTime timestamp;
    private String address;
    private UserDTO user;
    private List<ReceiptItemDTO> receiptItems;

    public ReceiptDTO() {}

    public ReceiptDTO(Long id, double totalPrice, LocalDateTime timestamp, String address, UserDTO user, List<ReceiptItemDTO> receiptItems) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
        this.address = address;
        this.user = user;
        this.receiptItems = receiptItems;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }

    public List<ReceiptItemDTO> getReceiptItems() { return receiptItems; }
    public void setReceiptItems(List<ReceiptItemDTO> receiptItems) { this.receiptItems = receiptItems; }
}
