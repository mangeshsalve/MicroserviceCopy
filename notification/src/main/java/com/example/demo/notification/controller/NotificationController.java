package com.example.demo.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.notification.dto.NotificationDto;
import com.example.demo.notification.service.NotificationService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private NotificationService service ;
	
	@GetMapping("/send")
	public String testing() {
		return "Testing";
	}
	@PostMapping("/send")
	public ResponseEntity<String> sendMail(@RequestBody NotificationDto notificationDto) {
		try {
			service.sendMail(notificationDto);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception found "+e.getMessage());
		}
		return ResponseEntity.ok("Message send ");
	}
}
