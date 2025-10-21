package bookstore.njtbookstore.service;

import bookstore.njtbookstore.domain.User;
import bookstore.njtbookstore.dto.AuthResponseDTO;
import bookstore.njtbookstore.dto.UserDTO;
import bookstore.njtbookstore.mapper.UserMapper;
import bookstore.njtbookstore.repository.UserRepository;
import bookstore.njtbookstore.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, UserMapper userMapper, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    // Get all users as DTO
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    // Find user by ID
    public UserDTO findById(Long id) throws Exception {
        return userMapper.toDto(userRepository.findById(id));
    }

    // Register a new user
    public UserDTO register(UserDTO dto) throws Exception {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        // Encrypt password
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    // Login and return JWT token
    public AuthResponseDTO login(String username, String password) throws Exception {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
            throw new Exception("Invalid username or password");
        }
        User user = userOpt.get();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new AuthResponseDTO(token, user.getRole());
    }

    // Update user role (ADMIN only)
    public void updateRole(Long userId, String newRole) throws Exception {
        User user = userRepository.findById(userId);
        user.setRole(newRole);
        userRepository.save(user);
    }
}
