package lottery.backend.repositories;

import lottery.entities.Statistics;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticsRepository extends CrudRepository<Statistics, Integer> {
    List<Statistics> getByPlayer(String playerName);
}
