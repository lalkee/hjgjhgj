/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.njtbookstore.mapper;

import bookstore.njtbookstore.domain.User;
import bookstore.njtbookstore.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class UserMapper implements DtoEntityMapper<UserDTO, User> {

    @Override
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        // ⚠️ Don't include password in DTO (for security)
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // ⚠️ Raw password — encode it in service/controller
        user.setRole(dto.getRole() != null ? dto.getRole() : "ROLE_USER");
        return user;
    }
}
