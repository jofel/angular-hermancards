package hu.szeged.u.ohsh.web.rest;
import hu.szeged.u.ohsh.domain.Room;
import hu.szeged.u.ohsh.service.RoomService;
import hu.szeged.u.ohsh.web.rest.errors.BadRequestAlertException;
import hu.szeged.u.ohsh.web.rest.util.HeaderUtil;
import hu.szeged.u.ohsh.web.rest.util.PaginationUtil;
import hu.szeged.u.ohsh.service.dto.RoomCriteria;
import hu.szeged.u.ohsh.service.RoomQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Room.
 */
@RestController
@RequestMapping("/api")
public class RoomResource {

    private final Logger log = LoggerFactory.getLogger(RoomResource.class);

    private static final String ENTITY_NAME = "room";

    private final RoomService roomService;

    private final RoomQueryService roomQueryService;

    public RoomResource(RoomService roomService, RoomQueryService roomQueryService) {
        this.roomService = roomService;
        this.roomQueryService = roomQueryService;
    }

    /**
     * POST  /rooms : Create a new room.
     *
     * @param room the room to create
     * @return the ResponseEntity with status 201 (Created) and with body the new room, or with status 400 (Bad Request) if the room has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) throws URISyntaxException {
        log.debug("REST request to save Room : {}", room);
        if (room.getId() != null) {
            throw new BadRequestAlertException("A new room cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Room result = roomService.save(room);
        return ResponseEntity.created(new URI("/api/rooms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rooms : Updates an existing room.
     *
     * @param room the room to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated room,
     * or with status 400 (Bad Request) if the room is not valid,
     * or with status 500 (Internal Server Error) if the room couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rooms")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) throws URISyntaxException {
        log.debug("REST request to update Room : {}", room);
        if (room.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Room result = roomService.save(room);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, room.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rooms : get all the rooms.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of rooms in body
     */
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms(RoomCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Rooms by criteria: {}", criteria);
        Page<Room> page = roomQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rooms");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /rooms/count : count all the rooms.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/rooms/count")
    public ResponseEntity<Long> countRooms(RoomCriteria criteria) {
        log.debug("REST request to count Rooms by criteria: {}", criteria);
        return ResponseEntity.ok().body(roomQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /rooms/:id : get the "id" room.
     *
     * @param id the id of the room to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the room, or with status 404 (Not Found)
     */
    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Long id) {
        log.debug("REST request to get Room : {}", id);
        Optional<Room> room = roomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(room);
    }

    /**
     * DELETE  /rooms/:id : delete the "id" room.
     *
     * @param id the id of the room to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        log.debug("REST request to delete Room : {}", id);
        roomService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
