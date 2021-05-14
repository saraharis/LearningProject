package MyLearningProject.restservice.cropService;

import MyLearningProject.restservice.models.DistrictSoil;

import java.sql.SQLException;
import java.util.HashMap;

public interface CropService {
    HashMap<String, String> findSuitableCrops(DistrictSoil ds, String season) throws SQLException;
}
