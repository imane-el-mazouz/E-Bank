package com.bank.service;

import com.bank.exception.CardNotFoundException;
import com.bank.model.Card;
import com.bank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(CardNotFoundException::new);
    }

    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteCard(Long id) {
        cardRepository.findById(id).orElseThrow(CardNotFoundException::new);
        cardRepository.deleteById(id);
    }

    public void updateCard(Long id, Card updatedCard) {
        Card existingCard = cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);

        existingCard.setExpirationDate(updatedCard.getExpirationDate());
        existingCard.setTypeCard(updatedCard.getTypeCard());
        existingCard.setStatus(updatedCard.getStatus());
        existingCard.setBlockingReason(updatedCard.getBlockingReason());
        existingCard.setAccount(updatedCard.getAccount());

        cardRepository.save(existingCard);
    }
}
