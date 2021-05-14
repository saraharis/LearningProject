package MyLearningProject.restservice;

import MyLearningProject.restservice.models.Climate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimateRepository extends JpaRepository<Climate, Long> {

}
