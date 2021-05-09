package MyLearningProject.restservice;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PincodeServiceImpl implements PincodeService{

    @Autowired
    private PincodeRepository pincodeRepository;

    @Override
    public Area getArea(String pincode){
        return pincodeRepository.findByPincode(pincode);
    }
    public List<Area> getAllAreas(){
        return pincodeRepository.findAll();
    }

}
