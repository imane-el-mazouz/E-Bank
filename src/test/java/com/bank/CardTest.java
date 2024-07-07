package com.bank;

import com.bank.enums.Reason;
import com.bank.enums.Status;
import com.bank.enums.TypeC;
import com.bank.exception.CardNotFoundException;
import com.bank.model.Card;
import com.bank.repository.CardRepository;
import com.bank.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardTest {

	@Mock
	private CardRepository cardRepository;

	@InjectMocks
	private CardService cardService;

	private Card card;

	@BeforeEach
	public void setUp() {
		card = new Card();
		card.setIdC(1L);
		card.setExpirationDate(LocalDateTime.parse("12/25"));
		card.setTypeCard(TypeC.valueOf("Credit"));
		card.setStatus(Status.valueOf("Active"));
		card.setBlockingReason(Reason.valueOf("None"));
		card.setAccount(null); // Assuming Account is a separate entity
	}

	@Test
	void testGetAllCards() {
		List<Card> cards = Arrays.asList(card);
		when(cardRepository.findAll()).thenReturn(cards);

		List<Card> result = cardService.getAllCards();

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(cardRepository, times(1)).findAll();
	}

	@Test
	void testGetCardById() {
		when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

		Card result = cardService.getCardById(1L);

		assertNotNull(result);
		assertEquals("12/25", result.getExpirationDate());
		verify(cardRepository, times(1)).findById(1L);
	}

	@Test
	void testGetCardByIdNotFound() {
		when(cardRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(CardNotFoundException.class, () -> cardService.getCardById(1L));
		verify(cardRepository, times(1)).findById(1L);
	}

	@Test
	void testSaveCard() {
		when(cardRepository.save(card)).thenReturn(card);

		Card result = cardService.saveCard(card);

		assertNotNull(result);
		assertEquals("12/25", result.getExpirationDate());
		verify(cardRepository, times(1)).save(card);
	}

	@Test
	void testDeleteCard() {
		when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

		cardService.deleteCard(1L);

		verify(cardRepository, times(1)).findById(1L);
		verify(cardRepository, times(1)).deleteById(1L);
	}

	@Test
	void testDeleteCardNotFound() {
		when(cardRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(CardNotFoundException.class, () -> cardService.deleteCard(1L));
		verify(cardRepository, times(1)).findById(1L);
		verify(cardRepository, times(0)).deleteById(1L);
	}

//	@Test
//	void testUpdateCard() {
//		Card updatedCard = new Card();
//		updatedCard.setExpirationDate("01/26");
//		updatedCard.setTypeCard("Debit");
//		updatedCard.setStatus("Inactive");
//		updatedCard.setBlockingReason("Lost");
//		updatedCard.setAccount(null);
//
//		when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
//		when(cardRepository.save(card)).thenReturn(card);
//
//		cardService.updateCard(1L, updatedCard);
//
//		verify(cardRepository, times(1)).findById(1L);
//		verify(cardRepository, times(1)).save(card);
//
//		assertEquals("01/26", card.getExpirationDate());
//		assertEquals("Debit", card.getTypeCard());
//		assertEquals("Inactive", card.getStatus());
//		assertEquals("Lost", card.getBlockingReason());
//		assertNull(card.getAccount());
//	}
//
//	@Test
//	void testUpdateCardNotFound() {
//		when(cardRepository.findById(1L)).thenReturn(Optional.empty());
//
//		Card updatedCard = new Card();
//		updatedCard.setExpirationDate("01/26");
//		updatedCard.setTypeCard("Debit");
//		updatedCard.setStatus("Inactive");
//		updatedCard.setBlockingReason("Lost");
//		updatedCard.setAccount(null);
//
//		assertThrows(CardNotFoundException.class, () -> cardService.updateCard(1L, updatedCard));
//		verify(cardRepository, times(1)).findById(1L);
//		verify(cardRepository, times(0)).save(any(Card.class));
//	}
}
