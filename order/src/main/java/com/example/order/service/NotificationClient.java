package com.example.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.order.dto.NotificationDto;

@FeignClient(name = "notification-service")
public interface NotificationClient {

	@PostMapping("/notifications/send")
	String sendNotification(NotificationDto dto);
}
