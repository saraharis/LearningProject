package MyLearningProject.restservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {

    Crop findByCropName(String cropName);
    Crop findById(int id);
}
