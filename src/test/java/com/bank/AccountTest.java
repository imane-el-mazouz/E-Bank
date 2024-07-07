package com.bank;
import com.bank.model.*;


import com.bank.enums.Bank;
import com.bank.enums.TypeA;
import com.bank.model.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AccountTest {

	@Mock
	private MockMvc mockMvc;
	@Mock
	private User mockUser;

	@Mock
	private Beneficiary mockBeneficiary;

	@Mock
	private Card mockCard;

	@InjectMocks
	private Account account;

	@BeforeEach
	public void setUp() {
		account.setIdA(1L);
		account.setTypeA(TypeA.currentAccount);
		account.setSold(1000.0);
		account.setDate(LocalDateTime.of(2023, 1, 1, 10, 0));
		account.setCloseureReason("Closed");
		account.setBank(Bank.cih);
		account.setUser(mockUser);
		account.setBeneficiaries(List.of(mockBeneficiary));
		account.setCards(List.of(mockCard));
	}

	@Test
	public void testGetters() {
		assertEquals(1L, account.getIdA());
		assertEquals(TypeA.currentAccount, account.getTypeA());
		assertEquals(1000.0, account.getSold());
		assertEquals(LocalDateTime.of(2023, 1, 1, 10, 0), account.getDate());
		assertEquals("Closed", account.getCloseureReason());
		assertEquals(Bank.cih, account.getBank());
		assertEquals(mockUser, account.getUser());
		assertEquals(List.of(mockBeneficiary), account.getBeneficiaries());
		assertEquals(List.of(mockCard), account.getCards());
	}

	@Test
	public void testNoArgsConstructor() {
		Account emptyAccount = new Account();
		assertNull(emptyAccount.getIdA());
		assertNull(emptyAccount.getTypeA());
		assertNull(emptyAccount.getSold());
		assertNull(emptyAccount.getDate());
		assertNull(emptyAccount.getCloseureReason());
		assertNull(emptyAccount.getBank());
		assertNull(emptyAccount.getUser());
		assertNull(emptyAccount.getBeneficiaries());
		assertNull(emptyAccount.getCards());
	}
//	@Test
//	public void testAllArgsConstructor() {
//		Account newAccount = new Account(2L, TypeA.savingAccount, 2000.0,
//				LocalDateTime.of(2023, 2, 1, 10, 0), "Reason", Bank.bmce,
//				mockUser, List.of(mockBeneficiary), List.of(mockCard));
//
//		assertEquals(2L, newAccount.getIdA());
//		assertEquals(TypeA.savingAccount, newAccount.getTypeA());
//		assertEquals(2000.0, newAccount.getSold());
//		assertEquals(LocalDateTime.of(2023, 2, 1, 10, 0), newAccount.getDate());
//		assertEquals("Reason", newAccount.getCloseureReason());
//		assertEquals(Bank.bmce, newAccount.getBank());
//		assertEquals(mockUser, newAccount.getUser());
//		assertEquals(List.of(mockBeneficiary), newAccount.getBeneficiaries());
//		assertEquals(List.of(mockCard), newAccount.getCards());
//	}


//	@Test
//	public void testGetAllAccounts() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/account")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andDo(MockMvcResultHandlers.print());
//	}


//	@Test
//	public void testGetAccountById() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/account/1")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andDo(MockMvcResultHandlers.print());
//	}

//	@Test
//	public void testSaveAccount() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
//						.content("{\"idA\": 66, \"typeA\": \"currentAccount\", \"sold\": 1000.0, \"date\": \"2023-01-01T10:00:00\", \"closeureReason\": \"Closed\", \"bank\": \"cih\",}")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void testDeleteAccount() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/1")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNoContent())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void testUpdateAccount() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.put("/api/account/update/1")
//						.content("{\"idA\": 1, \"typeA\": \"currentAccount\", \"sold\": 1000.0, \"date\": \"2023-01-01T10:00:00\", \"closeureReason\": \"Closed\", \"bank\": \"cih\", \"user\": {}, \"beneficiaries\": [], \"cards\": []}")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNoContent())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void testCloseAccount() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.put("/api/account/close/1")
//						.content("{\"reason\": \"Closed due to request\"}")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isNoContent())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void testGetAccountBalance() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/account/1/sold")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andDo(MockMvcResultHandlers.print());
//	}
//


//	@Test
//	public void testGetAccountTransactions() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/api/account/1/transactions")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andDo(MockMvcResultHandlers.print());
//	}
//
//	private Account createMockAccount() {
//		Account account = new Account();
//		account.setIdA(1L);
//		account.setTypeA(TypeA.currentAccount);
//		account.setSold(1000.0);
//		account.setDate(LocalDateTime.of(2023, 1, 1, 10, 0));
//		account.setCloseureReason("Closed");
//		account.setBank(Bank.cih);
//		return account;
//	}
//
//	private Transaction createMockTransaction() {
//		Transaction transaction = new Transaction();
//		return transaction;
//	}
}