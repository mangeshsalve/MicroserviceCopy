package com.example.demo.notification.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.notification.dto.NotificationDto;
import com.example.demo.notification.service.NotificationService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Override
	public void sendMail(NotificationDto dto) throws MessagingException {
	
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
		helper.setTo(dto.getTo());
		helper.setFrom(dto.getFrom());
		helper.setSubject("Order Placed");
		helper.setText(dto.getMessage());
		javaMailSender.send(mimeMessage);
	}
	

}
