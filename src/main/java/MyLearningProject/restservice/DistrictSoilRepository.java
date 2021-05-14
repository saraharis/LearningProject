package MyLearningProject.restservice;

import MyLearningProject.restservice.models.DistrictSoil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictSoilRepository extends JpaRepository<DistrictSoil, Long> {

    DistrictSoil findByDistrict(String district);

}
