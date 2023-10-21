package MyLearningProject.restservice;

import MyLearningProject.restservice.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PincodeRepository extends JpaRepository<Area, Long> {
    Area findByPincode(String pincode);
}