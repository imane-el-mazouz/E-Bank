package com.bank;
import com.bank.model.*;


import com.bank.enums.Bank;
import com.bank.enums.TypeA;
import com.bank.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

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
}
