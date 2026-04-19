package com.spotfinderbackend.notifications.domain.services;

import com.spotfinderbackend.notifications.domain.model.aggregates.Notification;
import com.spotfinderbackend.notifications.domain.model.queries.GetNotificationsByUserIdQuery;

import java.util.List;

public interface NotificationQueryService {

    List<Notification> handle(GetNotificationsByUserIdQuery query);
}