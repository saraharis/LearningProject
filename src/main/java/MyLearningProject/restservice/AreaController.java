package MyLearningProject.restservice;

import MyLearningProject.restservice.climateService.ClimateService;
import MyLearningProject.restservice.climateService.ClimateServiceImpl;
import MyLearningProject.restservice.cropService.CropService;
import MyLearningProject.restservice.cropService.CropServiceImpl;
import MyLearningProject.restservice.customerService.CustomerService;
import MyLearningProject.restservice.customerService.CustomerServiceImpl;
import MyLearningProject.restservice.models.Area;
import MyLearningProject.restservice.models.Customer;
import MyLearningProject.restservice.models.DistrictSoil;
import MyLearningProject.restservice.pincodeService.PincodeService;
import MyLearningProject.restservice.pincodeService.PincodeServiceImpl;
import MyLearningProject.restservice.soilService.SoilService;
import MyLearningProject.restservice.soilService.SoilServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.HashMap;

@Controller

public class AreaController {



    @RequestMapping("/")
    public String viewIndexPage(Model model) {
        return "zz";
    }


    @Autowired
    //private PincodeService pincodeService;
    PincodeService pincodeService = new PincodeServiceImpl();

    //enum soilProps {N, P, K}


    String currSeason = new String("s");
    String all = new String("a");



    @Autowired
    private PincodeRepository pincodeRepository;

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    //private CropService cropService;
    CropService cropService = new CropServiceImpl();

    @Autowired
    SoilService soilService = new SoilServiceImpl();

    @Autowired
    ClimateService climateService = new ClimateServiceImpl();

    @Autowired
    CustomerService customerService = new CustomerServiceImpl();

    @RequestMapping("/router2")
    public String viewRouter2(Model model, Model model2, @ModelAttribute("pincode") String pincode) throws SQLException {

        //Area region = pincodeRepository.findByPincode(pincode);

        if (pincodeService.isValidPincode(pincode) == false){
            System.out.println("invalid pincode entered");
            return "zz";
        }

        try {
            Area region = pincodeService.getArea(pincode);
            String district = region.getDistrict();
            DistrictSoil ds = soilService.findByDistrict(district.toLowerCase());

            System.out.println( " District being searched is " + district.toLowerCase());

            HashMap<String, String> imageMap2 = cropService.findSuitableCrops(ds, currSeason);
            //model.addAttribute("crops_possible", cropsPossible);

            String soilJson = new Gson().toJson(ds);
            model.addAttribute("district", soilJson);
            model2.addAttribute("hm", imageMap2);


            //Long resultVal = climateService.findTempByLocation(region.getLat(), region.getLon(), 1, "T2M");
            //System.out.println("resultVal = " + resultVal);

            // TODO: add a model which has the season code also

            return "crops";

        } catch (Exception e){
            System.out.println("unavailable pincode");
            Customer customer = new Customer();
            model.addAttribute("customer", customer);
            return "pincode_not_available";
        }


    }

    @RequestMapping("/save_customer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);

        return "thanks";

    }

    @RequestMapping("/test")
    public String test(Model model){
        DistrictSoil ds = soilService.findByDistrict("ernakulam");
        //model.addAttribute("district", new Gson().toJson(ds));
        String jobj = new Gson().toJson(ds);
        model.addAttribute("district", jobj);
        System.out.println(jobj);
        return "test";
    }













    /*
        @RequestMapping("/list")
    public String viewList(Model model) {

        //List<Area> listAreas = pincodeService.getAllAreas();

        //model.addAttribute("listAreas",pincodeService.getAllAreas());

        model.addAttribute("listAreas", pincodeRepository.findAll());
        System.out.println("hey controller");
        String pin = "690103";
        //System.out.println(pincodeService.getArea(pin));
        return "l";
    }

    @Autowired
    private DistrictSoilRepository districtSoilRepository;

    @RequestMapping("/dist")
    public String viewDistrict(Model model) {
        model.addAttribute("listDistricts", districtSoilRepository.findAll());
        System.out.println("hey controller 2");
        return "d";
    }

    @RequestMapping("/router")
    public String viewRouter(Model model, @ModelAttribute("pincode") String pincode) {
        //String pin = "686632";
        //System.out.println(pincodeRepository.findByPincode(pin));
        Area region = pincodeRepository.findByPincode(pincode);
        String district = region.getDistrict();
        DistrictSoil ds = districtSoilRepository.findByDistrict(district.toLowerCase());
        System.out.println(district.toLowerCase());

        model.addAttribute("d", ds);

        return "d_new";
    }
     */




}




