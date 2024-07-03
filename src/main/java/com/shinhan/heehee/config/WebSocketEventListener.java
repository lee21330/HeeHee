package com.shinhan.heehee.config;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class WebSocketEventListener {

    private Set<String> connectedUsers = new HashSet<>();
    private Map<String, Set<String>> topicSubscriptions = new HashMap<>();

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
        connectedUsers.add(sessionId);
        System.out.println("New connection: " + sessionId);
        System.out.println("Total connected users: " + connectedUsers.size());
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        connectedUsers.remove(sessionId);
        System.out.println("Disconnected: " + sessionId);
        System.out.println("Total connected users: " + connectedUsers.size());

        // Remove the user from all topic subscriptions
        topicSubscriptions.values().forEach(subscribers -> subscribers.remove(sessionId));
    }

    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
        String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
        String destination = (String) event.getMessage().getHeaders().get("simpDestination");

        topicSubscriptions.computeIfAbsent(destination, k -> new HashSet<>()).add(sessionId);
        System.out.println("Subscribed to " + destination + ": " + sessionId);
        System.out.println("Subscribers to " + destination + ": " + topicSubscriptions.get(destination).size());
    }

    @EventListener
    public void handleWebSocketUnsubscribeListener(SessionUnsubscribeEvent event) {
        String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");
        String destination = (String) event.getMessage().getHeaders().get("simpDestination");

        if (topicSubscriptions.containsKey(destination)) {
            topicSubscriptions.get(destination).remove(sessionId);
            System.out.println("Unsubscribed from " + destination + ": " + sessionId);
            System.out.println("Subscribers to " + destination + ": " + topicSubscriptions.get(destination).size());
        }
    }

    public int getSubscribersCount(String topic) {
        return topicSubscriptions.getOrDefault(topic, new HashSet<>()).size();
    }
}
