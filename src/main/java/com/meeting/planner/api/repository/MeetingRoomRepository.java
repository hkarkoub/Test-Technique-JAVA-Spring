package com.meeting.planner.api.repository;

import com.meeting.planner.api.model.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    // TO DO
}
