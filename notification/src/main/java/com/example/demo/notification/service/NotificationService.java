package com.example.demo.notification.service;

import com.example.demo.notification.dto.NotificationDto;

import jakarta.mail.MessagingException;

public interface NotificationService {

	void sendMail(NotificationDto dto) throws MessagingException;
}
