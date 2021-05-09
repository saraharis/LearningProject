package MyLearningProject.restservice;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface PincodeService {
    Area getArea(String pincode);
    List<Area> getAllAreas();
}
