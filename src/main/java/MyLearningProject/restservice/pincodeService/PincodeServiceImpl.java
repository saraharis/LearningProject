package MyLearningProject.restservice.pincodeService;

import MyLearningProject.restservice.models.Area;
import MyLearningProject.restservice.PincodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component

public class PincodeServiceImpl implements PincodeService {

    @Autowired
    private PincodeRepository pincodeRepository;

    @Override
    public Area getArea(String pincode){
        return pincodeRepository.findByPincode(pincode);
    }
    public List<Area> getAllAreas(){
        return pincodeRepository.findAll();
    }

    public boolean isValidPincode(String pincode){
        // Regex to check valid pin code of India.
        String regex
                = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the pin code is empty
        // return false
        if (pincode == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given pin code
        // and regular expression.
        Matcher m = p.matcher(pincode);

        // Return if the pin code
        // matched the ReGex
        return m.matches();

    }

}
