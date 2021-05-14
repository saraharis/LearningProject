package MyLearningProject.restservice.pincodeService;

import MyLearningProject.restservice.models.Area;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface PincodeService {
    Area getArea(String pincode);
    List<Area> getAllAreas();
    boolean isValidPincode(String pincode);
}
