package com.example.websocketserver.utils;

import com.example.websocketserver.model.Event;
import com.example.websocketserver.repository.EventRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;

@Component
public class WebSocketEventHandler extends TextWebSocketHandler {

    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;

    public WebSocketEventHandler(EventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message.getPayload());
            String eventType = jsonNode.get("eventType").asText();
            JsonNode payload = jsonNode.get("payload");

            if (eventType == null || payload == null) {
                session.sendMessage(new TextMessage("Invalid event format."));
                return;
            }

            Event event = new Event();
            event.setEventType(eventType);
            event.setPayload(payload.toString());
            event.setTimestamp(LocalDateTime.now());
            eventRepository.save(event);

            session.sendMessage(new TextMessage("Event processed successfully."));
        } catch (Exception e) {
            try {
                session.sendMessage(new TextMessage("Error processing event: " + e.getMessage()));
            } catch (Exception ignored) { }
        }
    }
}

