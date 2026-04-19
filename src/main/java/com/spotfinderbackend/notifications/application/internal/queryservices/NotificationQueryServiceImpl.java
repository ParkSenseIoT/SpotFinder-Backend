package com.spotfinderbackend.notifications.application.internal.queryservices;

import com.spotfinderbackend.notifications.domain.model.aggregates.Notification;
import com.spotfinderbackend.notifications.domain.model.queries.GetNotificationsByUserIdQuery;
import com.spotfinderbackend.notifications.domain.services.NotificationQueryService;
import com.spotfinderbackend.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository repository;

    public NotificationQueryServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Notification> handle(GetNotificationsByUserIdQuery query) {

        return repository.findByUserId(query.userId());
    }
}