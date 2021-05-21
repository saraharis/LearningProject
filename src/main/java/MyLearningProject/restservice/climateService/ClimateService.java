package MyLearningProject.restservice.climateService;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service

public interface ClimateService {

    Long findTempByLocation(int month, String parameter, String pincode) throws SQLException;

}
