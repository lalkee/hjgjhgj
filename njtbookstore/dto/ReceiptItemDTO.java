/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.dto;

/**
 *
 * @author Milan
 */
public class ReceiptItemDTO {

    private Long id;
    private int itemNo;
    private double price;
    private int amount;
    private BookDTO book;

    public ReceiptItemDTO() {
    }

    public ReceiptItemDTO(Long id, int itemNo, double price, int amount, BookDTO book) {
        this.id = id;
        this.itemNo = itemNo;
        this.price = price;
        this.amount = amount;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
