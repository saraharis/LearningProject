package MyLearningProject.restservice;

import MyLearningProject.restservice.climateService.ClimateService;
import MyLearningProject.restservice.climateService.ClimateServiceImpl;
import MyLearningProject.restservice.cropService.CropService;
import MyLearningProject.restservice.cropService.CropServiceImpl;
import MyLearningProject.restservice.customerService.CustomerService;
import MyLearningProject.restservice.customerService.CustomerServiceImpl;
import MyLearningProject.restservice.mathService.MathServiceImpl;
import MyLearningProject.restservice.models.Area;
import MyLearningProject.restservice.models.Crop;
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
import java.util.List;

@Controller

public class AreaController {


    @RequestMapping("/")
    public String viewIndexPage(Model model) {
        return "home";
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

    @Autowired
    MathServiceImpl mathService = new MathServiceImpl();



    @RequestMapping("/router2")
    public String viewRouter2(Model model1, Model model2, Model model3, Model model4, Model model5, @ModelAttribute("pincode") String pincode) throws SQLException {

        //Area region = pincodeRepository.findByPincode(pincode);

        if (pincodeService.isValidPincode(pincode) == false){
            System.out.println("invalid pincode entered");
            return "home";
        }

        try {
            Area region = pincodeService.getArea(pincode);
            String district = region.getDistrict();
            DistrictSoil ds = soilService.findByDistrict(district.toLowerCase());
            System.out.println( " District being searched is " + district.toLowerCase());

            String parameter = "T2M"; //code for temperature parameter
            int month = 5; //may month
            String monthString = "may";

            Long temp = climateService.findTempByLocation(month,parameter,pincode);
            Long ph = ds.getpH();
            System.out.println("temp is" + temp);
            System.out.println("district's ph is " + ph);

            List<Crop> emptyCropDataList = cropService.findEmpty();
            List<Crop> soilCropList = cropService.findCropsBySoil(ds);
            List<Crop> cropsBySeason = cropService.findCropsBySeason(currSeason);
            List<Crop> cropsByTemp = cropService.findCropsByTemp(temp);
            List<Crop> cropsByph = cropService.findCropsByPh(ph);
            List<Crop> union = mathService.findUnionOf(emptyCropDataList, soilCropList);
            //List<Crop> phIntersection = mathService.findIntersectionOf(union,cropsByph);
            List<Crop> finalCropList = mathService.findIntersectionOf(union, cropsByTemp);

            for (Crop crop: finalCropList){
                System.out.println(crop.getCropName());
            }
            HashMap<String, String> imageMap = cropService.getImageMap(finalCropList);
            model2.addAttribute("hm", imageMap);

            String soilJson = new Gson().toJson(ds);
            model1.addAttribute("district", soilJson);

            model3.addAttribute("tempData", temp.toString());
            model4.addAttribute("month", monthString);




            //Long resultVal = climateService.findTempByLocation(region.getLat(), region.getLon(), 1, "T2M");
            //System.out.println("resultVal = " + resultVal);



            return "crop";

        } catch (Exception e){
            System.out.println("unavailable pincode");
            Customer customer = new Customer();
            customer.setPincode(pincode);
            System.out.println(customer.getPincode());
            model5.addAttribute("customer", customer);
            return "pincode_not_available";
        }


    }

    @RequestMapping("/save_customer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);

        return "thanks";

    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("foodprint")
    public String foodprint(){ return "foodprint";}












    @RequestMapping("/test")
    public String test(Model model) throws SQLException {
        /*DistrictSoil ds = soilService.findByDistrict("ernakulam");
        //model.addAttribute("district", new Gson().toJson(ds));
        String jobj = new Gson().toJson(ds);
        model.addAttribute("district", jobj);
        System.out.println(jobj);*/
        //Crop crop;
        DistrictSoil ds = soilService.findByDistrict("ernakulam");
        List<Crop> emptyCropDataList = cropService.findEmpty();
        List<Crop> soilCropList = cropService.findCropsBySoil(ds);
        List<Crop> cropsBySeason = cropService.findCropsBySeason(currSeason);
        /*List<Crop> finalCropList = cropService.finalCropList(emptyCropDataList, cropsBySeason,soilCropList );
        for (Crop crop: finalCropList){
            System.out.println(crop.getCropName());
        }
        HashMap<String, String> imageMap = cropService.getImageMap(finalCropList);*/
        //model.addAttribute("hm", imageMap);

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




