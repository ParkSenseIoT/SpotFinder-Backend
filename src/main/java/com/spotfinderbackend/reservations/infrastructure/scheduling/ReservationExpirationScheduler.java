package com.spotfinderbackend.reservations.infrastructure.scheduling;

import com.spotfinderbackend.reservations.domain.model.commands.ExpireGracePeriodCommand;
import com.spotfinderbackend.reservations.domain.services.ReservationCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Background sweeper that expires PENDING reservations whose grace period elapsed.
 * Runs every minute. Light-weight: in production we'd replace the {@code findAll}
 * filter inside the repository by a paged query.
 */
@Component
public class ReservationExpirationScheduler {

    private static final Logger log = LoggerFactory.getLogger(ReservationExpirationScheduler.class);

    private final ReservationCommandService commandService;

    public ReservationExpirationScheduler(ReservationCommandService commandService) {
        this.commandService = commandService;
    }

    /** Executes every 60 000 ms (1 min). Set {@code fixedRate} so it never overlaps with itself. */
    @Scheduled(fixedRate = 60_000)
    public void sweepExpirations() {
        int expired = commandService.handle(new ExpireGracePeriodCommand());
        if (expired > 0) {
            log.info("Reservation expiration sweep: {} reservation(s) expired", expired);
        }
    }
}
