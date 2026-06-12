package com.ncv.LinkedInProject.notificationService.repository;

import com.ncv.LinkedInProject.notificationService.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
