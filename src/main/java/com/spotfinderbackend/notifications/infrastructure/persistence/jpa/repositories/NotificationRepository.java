package com.spotfinderbackend.notifications.infrastructure.persistence.jpa.repositories;

import com.spotfinderbackend.notifications.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);
}