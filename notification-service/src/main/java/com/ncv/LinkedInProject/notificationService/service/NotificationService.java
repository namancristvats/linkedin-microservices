package com.ncv.LinkedInProject.notificationService.service;

import com.ncv.LinkedInProject.notificationService.entity.Notification;
import com.ncv.LinkedInProject.notificationService.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    public void addNotification(Notification notification){
        log.info("Adding notification to db, messages: {}",notification.getMessage());
        notification=notificationRepository.save(notification);
        //SendMailer to send email
        //FCM

    }
}
