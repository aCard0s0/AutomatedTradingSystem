package cryptobot.mws.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * This interface is used to start the service.
 */
public interface MarketWatcherService {
    /**
     * This method is used after the application is ready and listening for requests.
     */
    @EventListener(ApplicationReadyEvent.class)
    void startMarketTasks();
}
