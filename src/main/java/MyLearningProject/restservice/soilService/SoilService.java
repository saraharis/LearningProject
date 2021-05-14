package MyLearningProject.restservice.soilService;

import MyLearningProject.restservice.models.DistrictSoil;
import org.springframework.stereotype.Service;

@Service
public interface SoilService {
    DistrictSoil findByDistrict(String district);

}
