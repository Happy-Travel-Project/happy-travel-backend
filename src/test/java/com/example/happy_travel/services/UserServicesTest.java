package com.example.happy_travel.services;

import com.example.happy_travel.dtos.user.UserRequest;
import com.example.happy_travel.dtos.user.UserResponse;
import com.example.happy_travel.exceptions.EntityAlreadyExistsException;
import com.example.happy_travel.models.User;
import com.example.happy_travel.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.example.happy_travel.models.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServicesTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;
    private User user1Entity;
    private User user2Entity;
    private UserRequest user1Request;
    private UserRequest user1UpdatedRequest;
    private UserResponse user1ExpectedRequest;
    private Long user1Id;
    private User userEntityWithId;
    private User userEntityWithIdUpdated;

    @BeforeEach
    void setup() {
        user1Id = 1L;
        user1Entity = new User("More", "more@gmail.com", "encodedPassword", USER);
        user2Entity = new User("Loli", "moredev@gmail.com", "2yU#2yU#", USER);
        user1Request = new UserRequest("More", "more@gmail.com", "2yU#2yU#", "USER");
        user1UpdatedRequest = new UserRequest("More", "more@gmail.com", "2yU#2yU#", "USER");
        user1ExpectedRequest = new UserResponse(1L, "More", "morena@gmail.com", "ROLE_USER");
        userEntityWithId = new User(1L, "More", "more@gmail.com", "encodedPassword", USER);
        userEntityWithIdUpdated = new User(1L, "More", "morena@gmail.com", "encodedPassword", USER);
    }

    @Test
    void getAllUsers_whenUsersExist_returnsListOfUsersResponse() {
        when(userRepository.findAll()).thenReturn(List.of(user1Entity, user2Entity));

        List<UserResponse> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("More", result.getFirst().username());
        assertEquals("more@gmail.com", result.getFirst().email());
        assertEquals("Loli", result.get(1).username());
        assertEquals("moredev@gmail.com", result.get(1).email());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void addUser_whenCorrectRequest_returnsUserResponse() {
        when(passwordEncoder.encode(user1Request.password())).thenReturn("encodedPassword");
        when(userRepository.existsByUsername(user1Request.username())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(userEntityWithId);

        UserResponse result = userService.addUser(user1Request);

        assertNotNull(result);
        assertEquals(UserResponse.class, result.getClass());
        assertEquals("More", result.username());
        assertEquals("more@gmail.com", result.email());
        assertEquals("ROLE_USER", result.role());

        verify(userRepository, times(1)).existsByUsername(user1Request.username());
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(user1Request.password());
    }

    @Test
    void addUser_whenUsernameAlreadyExists_throwsException() {
        when(userRepository.existsByUsername(user1Request.username())).thenReturn(true);

        EntityAlreadyExistsException exception = assertThrows(EntityAlreadyExistsException.class, () -> userService.addUser(user1Request));

        assertEquals("User with username More already exists", exception.getMessage());
    }

    @Test
    void updateUser_whenUserExists_returnsUserResponse() {
        when(userRepository.findById(user1Id)).thenReturn(Optional.of(userEntityWithId));
        when(userRepository.save(any(User.class))).thenReturn(userEntityWithIdUpdated);
        when(passwordEncoder.encode(user1UpdatedRequest.password())).thenReturn("encodedPassword");

        UserResponse result = userService.updateUser(user1Id, user1UpdatedRequest);

        assertEquals(user1ExpectedRequest, result);
        verify(userRepository, times(1)).findById(user1Id);
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(user1Request.password());
    }

    @Test
    void deleteUser_whenUserExists_returnsVoid(){
        when(userRepository.existsById(eq(user1Id))).thenReturn(true);
        when(userRepository.findById(user1Id)).thenReturn(Optional.of(userEntityWithId));
        doNothing().when(userRepository).deleteById(user1Id);

        userService.deleteUser(user1Id);

        verify(userRepository, times(1)).existsById(user1Id);
        verify(userRepository, times(1)).findById(user1Id);
        verify(userRepository, times(1)).deleteById(user1Id);

    }
}
