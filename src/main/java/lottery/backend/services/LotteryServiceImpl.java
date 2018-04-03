package lottery.backend.services;

import lottery.backend.repositories.StatisticsRepository;
import lottery.entities.Statistics;
import lottery.entities.Ticket;
import lottery.backend.constants.Winnings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class LotteryServiceImpl implements LotteryService {

    private static final Logger logger = LogManager.getLogger(LotteryServiceImpl.class.getName());

    @Autowired
    private StatisticsRepository statisticsRepository;

    /**
     * Makes a set out of 6 numbers the player chose in their ticket.
     *
     * @param ticket
     * @return a set of player´s 6 numbers
     */
    private Set<Integer> loadTicket(Ticket ticket) {

        Set<Integer> numbers = new HashSet<>();

        numbers.add(ticket.getFirstNumber());
        numbers.add(ticket.getSecondNumber());
        numbers.add(ticket.getThirdNumber());
        numbers.add(ticket.getFourthNumber());
        numbers.add(ticket.getFifthNumber());
        numbers.add(ticket.getSixthNumber());

        return numbers;

    }

    /**
     * Makes a set out of 6 unique random numbers ranging between 1 and 49
     *
     * @return a set of 6 random numbers
     */
    private Set<Integer> draw() {

        Set winningNumbers = new HashSet<Integer>();

        Random random = new Random();

        while (winningNumbers.size() != 6) {
            Integer randomNumber = random.nextInt(49) + 1;
            winningNumbers.add(randomNumber);
        }

        return winningNumbers;

    }

    /**
     * Compares player´s ticket with drawn numbers.
     *
     * @param numbers        a set representing player's numbers
     * @param winningNumbers a set of 6 drawn numbers
     * @return total of numbers present in both sets
     */
    private Integer compare(Set<Integer> numbers, Set<Integer> winningNumbers) {

        int numbersHit = 0;

        for (Integer winningNumber : winningNumbers) {
            if (numbers.contains(winningNumber)) {
                numbersHit++;
            }
        }

        return numbersHit;
    }

    @Async
    @Override
    public void startLottery(Ticket ticket) {

        Statistics statistics = new Statistics();

        statistics.setTicket(ticket);
        statistics.setPlayer(ticket.getPlayer());

        statistics.setCreated(LocalDateTime.now());
        statistics.setState("created");
        statisticsRepository.save(statistics);
        logger.info("Created statistics with ID" + statistics.getId());

        statistics.setStarted(LocalDateTime.now());
        statistics.setState("running");
        statisticsRepository.save(statistics);
        logger.info("Statistics with ID" + statistics.getId() + "is running");

        try {
            countLottery(statistics);
            statistics.setState("finished");
        } catch (Exception e) {
            statistics.setState("error");
            logger.error(e);
        } finally {
            statistics.setFinished(LocalDateTime.now());
            statisticsRepository.save(statistics);
            logger.info("Finished statistics with ID" + statistics.getId());
        }
    }

    /**
     * Compares 6 drawn numbers(they change with every attempt) to 6 player´s numbers (they stay
     * the same) as many times as player chose at the beginning of the play.
     * Updates #{@link Statistics} every draw.
     *
     * @param statistics
     */
    private void countLottery(Statistics statistics) {

        Integer balance = 0;
        Integer attempts;
        Set<Integer> numbers = loadTicket(statistics.getTicket());
        Integer draws = statistics.getTicket().getDraws();
        Map<Integer, Integer> numbersHitMap = new HashMap<>();

        for (Integer i = 0; i < 7; i++) {
            numbersHitMap.put(i, 0);
        }

        for (attempts = 0; attempts < draws; attempts++) {
            Set<Integer> winningNumbers = draw();
            int numbersHit = compare(numbers, winningNumbers);
            int prize = Winnings.WINNINGS.get(numbersHit);
            balance = balance - Winnings.TICKET_PRICE;
            balance = balance + prize;
            Integer tmp = numbersHitMap.get(numbersHit);
            numbersHitMap.put(numbersHit, tmp + 1);
        }

        statistics.setAttempts(attempts);
        statistics.setBalance(balance);
        statistics.setNumbersHitMap(numbersHitMap.toString());
    }

    @Override
    public boolean validate(Ticket ticket) {
        return (loadTicket(ticket).size() != 6);
    }

    @Override
    public Iterable<Statistics> getStatistics() {
        return statisticsRepository.findAll();
    }

    @Override
    public Iterable<Statistics> getStatisticsByPlayer(String playerName) {
        return statisticsRepository.getByPlayer(playerName);
    }
}
