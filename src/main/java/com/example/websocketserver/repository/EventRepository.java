package com.example.websocketserver.repository;

import com.example.websocketserver.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
