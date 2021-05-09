package MyLearningProject.restservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictSoilRepository extends JpaRepository<DistrictSoil, Long> {

    DistrictSoil findByDistrict(String district);

}
