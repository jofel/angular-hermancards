package hu.szeged.u.ohsh.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import hu.szeged.u.ohsh.domain.Card;
import hu.szeged.u.ohsh.domain.*; // for static metamodels
import hu.szeged.u.ohsh.repository.CardRepository;
import hu.szeged.u.ohsh.service.dto.CardCriteria;

/**
 * Service for executing complex queries for Card entities in the database.
 * The main input is a {@link CardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Card} or a {@link Page} of {@link Card} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CardQueryService extends QueryService<Card> {

    private final Logger log = LoggerFactory.getLogger(CardQueryService.class);

    private final CardRepository cardRepository;

    public CardQueryService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Return a {@link List} of {@link Card} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Card> findByCriteria(CardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Card> specification = createSpecification(criteria);
        return cardRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Card} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Card> findByCriteria(CardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Card> specification = createSpecification(criteria);
        return cardRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Card> specification = createSpecification(criteria);
        return cardRepository.count(specification);
    }

    /**
     * Function to convert CardCriteria to a {@link Specification}
     */
    private Specification<Card> createSpecification(CardCriteria criteria) {
        Specification<Card> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Card_.id));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Card_.createdDate));
            }
            if (criteria.getLosted() != null) {
                specification = specification.and(buildSpecification(criteria.getLosted(), Card_.losted));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), Card_.active));
            }
            if (criteria.getStudentId() != null) {
                specification = specification.and(buildSpecification(criteria.getStudentId(),
                    root -> root.join(Card_.student, JoinType.LEFT).get(Student_.id)));
            }
        }
        return specification;
    }
}
