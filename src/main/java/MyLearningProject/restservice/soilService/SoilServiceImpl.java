package MyLearningProject.restservice.soilService;

import MyLearningProject.restservice.models.DistrictSoil;
import MyLearningProject.restservice.DistrictSoilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SoilServiceImpl implements SoilService {

    @Autowired
    private DistrictSoilRepository districtSoilRepository;

    @Override
    public DistrictSoil findByDistrict(String district) {

        DistrictSoil ds = districtSoilRepository.findByDistrict(district.toLowerCase());
        return ds;
    }
}
