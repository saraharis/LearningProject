package MyLearningProject.restservice.climateService;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service

public interface ClimateService {

    Long findTempByLocation(Long lat, Long lon, int month, String parameter) throws SQLException;

}
