package com.bank;

import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountService accountService;

//	@Test
//	public void testTransferMoney() {
//		Account sourceAccount = new Account(/* initialize with mock data */);
//		Account destinationAccount = new Account(/* initialize with mock data */);
//		double amount = 100.0;
//
//		// Mock repository method
//		when(accountRepository.findById(anyLong())).thenReturn(Optional.of(sourceAccount));
//		when(accountRepository.save(any(Account.class))).thenReturn(sourceAccount);
//
//		accountService.transferMoney(sourceAccountId, destinationAccountId, amount);
//
//		// Assertion
//		assertEquals(expectedBalance, sourceAccount.getBalance());
//		verify(accountRepository, times(1)).findById(anyLong());
//		verify(accountRepository, times(1)).save(any(Account.class));
//	}
}
