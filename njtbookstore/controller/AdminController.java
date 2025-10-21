package bookstore.njtbookstore.controller;

import bookstore.njtbookstore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

@PreAuthorize("hasRole('ROLE_ADMIN')")
@PutMapping("/users/{id}/role")
public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestParam String role) {
    try {
        userService.updateRole(id, role);
        return ResponseEntity.ok().build();
    } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

}
