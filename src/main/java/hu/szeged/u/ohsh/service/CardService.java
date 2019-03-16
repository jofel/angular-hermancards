package hu.szeged.u.ohsh.service;

import hu.szeged.u.ohsh.domain.Card;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Card.
 */
public interface CardService {

    /**
     * Save a card.
     *
     * @param card the entity to save
     * @return the persisted entity
     */
    Card save(Card card);

    /**
     * Get all the cards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Card> findAll(Pageable pageable);


    /**
     * Get the "id" card.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Card> findOne(Long id);

    /**
     * Delete the "id" card.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
