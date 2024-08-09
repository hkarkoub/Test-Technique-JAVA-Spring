package com.meeting.planner.api.controller;

import com.meeting.planner.api.model.MeetingDetails;
import com.meeting.planner.api.model.MeetingRoom;
import com.meeting.planner.api.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetingrooms")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService service;

    @GetMapping
    public List<MeetingRoom> getAllMeetingRooms() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingRoom> getMeetingRoomById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MeetingRoom createMeetingRoom(@RequestBody MeetingRoom room) {
        return service.save(room);
    }

    @PostMapping("/findAvailableRoom")
    public List<MeetingRoom> findAvailableRoom(@RequestBody MeetingDetails meetingDetails){
    	 return service.findAvailableRoom(meetingDetails);   	
    	
    }
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