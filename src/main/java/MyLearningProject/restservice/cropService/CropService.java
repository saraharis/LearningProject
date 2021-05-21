package MyLearningProject.restservice.cropService;

import MyLearningProject.restservice.models.Crop;
import MyLearningProject.restservice.models.DistrictSoil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface CropService {
    HashMap<String, String> getImageMap(List<Crop> finalCropList);

    HashMap<String, String> findSuitableCrops(DistrictSoil ds, String season) throws SQLException;
    List<Crop> findCropsByTemp(Long temp);
    List<Crop> findCropsBySoil(DistrictSoil ds) throws SQLException;
    List<Crop> findEmpty() throws SQLException;
    List<Crop> findCropsBySeason(String season);
    List<Crop> findCropsByPh(Long ph);

}
