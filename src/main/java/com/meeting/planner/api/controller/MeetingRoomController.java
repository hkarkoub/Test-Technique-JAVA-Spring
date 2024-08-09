package com.meeting.planner.api.controller;

import com.meeting.planner.api.model.MeetingDetails;
import com.meeting.planner.api.model.MeetingRoom;
import com.meeting.planner.api.service.MeetingRoomService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller pour gérer les opérations CRUD sur les salles de réunion.
 * 
 * <p>Cette classe expose des endpoints pour créer, lire, mettre à jour, et supprimer
 * des salles de réunion. Elle inclut également un endpoint pour trouver une salle
 * disponible en fonction des détails de la réunion.</p>
 * 
 * @author hkarkoub
 */
@RestController
@RequestMapping("/api/meetingrooms")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService service;

    /**
     * Récupère toutes les salles de réunion.
     *
     * @return une liste de toutes les salles de réunion
     */
    @GetMapping
    public List<MeetingRoom> getAllMeetingRooms() {
        return service.findAll();
    }

    /**
     * Récupère une salle de réunion par son identifiant.
     *
     * @param id l'identifiant de la salle de réunion
     * @return la salle de réunion si trouvée, sinon une réponse HTTP 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MeetingRoom> getMeetingRoomById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée une nouvelle salle de réunion.
     *
     * @param room les détails de la nouvelle salle de réunion
     * @return la salle de réunion créée
     */
    @PostMapping
    public MeetingRoom createMeetingRoom(@RequestBody MeetingRoom room) {
        return service.save(room);
    }

    /**
     * Trouve les salles de réunion disponibles en fonction des détails de la réunion.
     *
     * @param meetingDetails les détails de la réunion
     * @return une liste de salles de réunion disponibles
     */
    @PostMapping("/findAvailableRoom")
    public List<MeetingRoom> findAvailableRoom(@RequestBody MeetingDetails meetingDetails){
        return service.findAvailableRoom(meetingDetails);   
    }
    
    /**
     * Met à jour une salle de réunion existante.
     *
     * @param id l'identifiant de la salle de réunion à mettre à jour
     * @param room les nouveaux détails de la salle de réunion
     * @return la salle de réunion mise à jour si trouvée, sinon une réponse HTTP 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<MeetingRoom> updateMeetingRoom(@PathVariable Long id, @RequestBody MeetingRoom room) {
        return service.findById(id)
                .map(existingRoom -> {
                    existingRoom.setName(room.getName());
                    existingRoom.setCapacity(room.getCapacity());
                    existingRoom.setAvailable(room.isAvailable());
                    return ResponseEntity.ok(service.save(existingRoom));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime une salle de réunion par son identifiant.
     *
     * @param id l'identifiant de la salle de réunion à supprimer
     * @return une réponse HTTP 204 No Content si la suppression est réussie, sinon une réponse HTTP 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMeetingRoom(@PathVariable Long id) {
        return service.findById(id)
                .map(room -> {
                    service.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
