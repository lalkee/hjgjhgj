/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.dto;

/**
 *
 * @author Milan
 */
public class AuthResponseDTO implements DtoMarker {
    private String token; // optional if using JWT
    private String role;

    public AuthResponseDTO() {}
    public AuthResponseDTO(String token, String role) {
        this.token = token;
        this.role = role;
    }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
