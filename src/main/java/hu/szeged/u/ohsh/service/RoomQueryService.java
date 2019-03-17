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

import hu.szeged.u.ohsh.domain.Room;
import hu.szeged.u.ohsh.domain.*; // for static metamodels
import hu.szeged.u.ohsh.repository.RoomRepository;
import hu.szeged.u.ohsh.service.dto.RoomCriteria;

/**
 * Service for executing complex queries for Room entities in the database.
 * The main input is a {@link RoomCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Room} or a {@link Page} of {@link Room} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RoomQueryService extends QueryService<Room> {

    private final Logger log = LoggerFactory.getLogger(RoomQueryService.class);

    private final RoomRepository roomRepository;

    public RoomQueryService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Return a {@link List} of {@link Room} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Room> findByCriteria(RoomCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Room> specification = createSpecification(criteria);
        return roomRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Room} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Room> findByCriteria(RoomCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Room> specification = createSpecification(criteria);
        return roomRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RoomCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Room> specification = createSpecification(criteria);
        return roomRepository.count(specification);
    }

    /**
     * Function to convert RoomCriteria to a {@link Specification}
     */
    private Specification<Room> createSpecification(RoomCriteria criteria) {
        Specification<Room> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Room_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Room_.name));
            }
            if (criteria.getSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSize(), Room_.size));
            }
        }
        return specification;
    }
}
