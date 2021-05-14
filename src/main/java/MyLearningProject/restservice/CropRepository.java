package MyLearningProject.restservice;

import MyLearningProject.restservice.models.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    Crop findByCropName(String cropName);
    Crop findById(int id);
}
