package lottery.backend.services;

import lottery.entities.Statistics;
import lottery.entities.Ticket;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public interface LotteryService {

    /**
     * Starts lottery and saves statistics in our database
     *
     * @param ticket
     */
    @Async
    void startLottery(Ticket ticket);

    /**
     * Checks if player adds exactly 6 numbers to their ticket.
     *
     * @param ticket
     * @return true, if ticket has exactly 6 numbers, otherwise returns false
     */
    boolean validate(Ticket ticket);

    /**
     * @return statistics for all players
     */
    Iterable<Statistics> getStatistics();

    /**
     * @param playerName
     * @return statistics for player with playerName
     */
    Iterable<Statistics> getStatisticsByPlayer(String playerName);
}
