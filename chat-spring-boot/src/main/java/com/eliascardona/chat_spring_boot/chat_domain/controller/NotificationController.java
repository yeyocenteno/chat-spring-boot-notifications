package com.eliascardona.chat_spring_boot.controller;

import com.eliascardona.chat_spring_boot.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/email")
    public ResponseEntity<?> sendEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String subject = request.get("subject");
        String message = request.get("message");

        notificationService.sendEmail(email, subject, message);
        return ResponseEntity.ok(Map.of("message", "Correo enviado exitosamente a " + email));
    }

    @PostMapping("/push")
    public ResponseEntity<?> sendPush(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String title = request.get("title");
        String body = request.get("body");

        String response = notificationService.sendPushNotification(token, title, body);
        return ResponseEntity.ok(Map.of("message", "Notificación Push enviada con éxito", "firebase_response", response));
    }
}