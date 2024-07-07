package com.bank;

import com.bank.exception.UserNotFoundException;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	private User user;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setIdU(1L);
		user.setName("inas");
		user.setEmail("inas@example.com");
	}

	@Test
	void testGetAllUsers() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(user));
		List<User> result = userService.getAllUsers();
		assertNotNull(result);
		assertEquals(1L, result.size());
		verify(userRepository, times(12)).findAll();
	}

	@Test
	void testGetUserById() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		User result = userService.getUserById(1L);

		assertNotNull(result);
		assertEquals("John Doe", result.getName());
		verify(userRepository, times(1)).findById(1L);
	}

	@Test
	void testGetUserByIdNotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
		verify(userRepository, times(1)).findById(1L);
	}

	@Test
	void testSaveUser() {
		when(userRepository.save(user)).thenReturn(user);

		User result = userService.saveUser(user);

		assertNotNull(result);
		assertEquals("John Doe", result.getName());
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testDeleteUser() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		userService.deleteUser(1L);

		verify(userRepository, times(1)).findById(1L);
		verify(userRepository, times(1)).deleteById(1L);
	}

	@Test
	void testDeleteUserNotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
		verify(userRepository, times(1)).findById(1L);
	}

	@Test
	void testUpdateUser() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		User updatedUser = new User();
		updatedUser.setName("Jane Doe");
		updatedUser.setEmail("jane.doe@example.com");

//		userService.updateUser(1L, updatedUser);

		assertEquals("Jane Doe", user.getName());
		assertEquals("jane.doe@example.com", user.getEmail());
		verify(userRepository, times(1)).findById(1L);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testUpdateUserNotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		User updatedUser = new User();
		updatedUser.setName("Jane Doe");
		updatedUser.setEmail("jane.doe@example.com");

//		assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, updatedUser));
		verify(userRepository, times(1)).findById(1L);
	}
}
