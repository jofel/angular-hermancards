package hu.szeged.u.ohsh.service.impl;

import hu.szeged.u.ohsh.service.CardService;
import hu.szeged.u.ohsh.domain.Card;
import hu.szeged.u.ohsh.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Card.
 */
@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Save a card.
     *
     * @param card the entity to save
     * @return the persisted entity
     */
    @Override
    public Card save(Card card) {
        log.debug("Request to save Card : {}", card);
        return cardRepository.save(card);
    }

    /**
     * Get all the cards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Card> findAll(Pageable pageable) {
        log.debug("Request to get all Cards");
        return cardRepository.findAll(pageable);
    }


    /**
     * Get one card by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Card> findOne(Long id) {
        log.debug("Request to get Card : {}", id);
        return cardRepository.findById(id);
    }

    /**
     * Delete the card by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Card : {}", id);
        cardRepository.deleteById(id);
    }
}
