package MyLearningProject.restservice;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Controller

public class AreaController {

    //@Autowired
    //MyLearningProject.restservice.PincodeService pincodeService = new MyLearningProject.restservice.PincodeServiceImpl();

    //@Autowired
    //private PincodeService pincodeService;
    //enum soilProps {N, P, K}

    String currSeason = new String("s");
    String all = new String("a");



    @Autowired
    private PincodeRepository pincodeRepository;

    @RequestMapping("/")
    public String viewIndexPage(Model model) {
        return "zz";
    }

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

    @Autowired
    private CropRepository cropRepository;

    @RequestMapping("/router2")
    public String viewRouter2(Model model, Model model2, @ModelAttribute("pincode") String pincode) throws SQLException {
        //String pin = "686632";
        //System.out.println(pincodeRepository.findByPincode(pin));
        Area region = pincodeRepository.findByPincode(pincode);
        String district = region.getDistrict();
        DistrictSoil ds = districtSoilRepository.findByDistrict(district.toLowerCase());
        System.out.println( " District being searched" + district.toLowerCase());

        //logic for getting suitable plants

        ///Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection

        String mysqlUrl = "jdbc:mysql://localhost/pincodes";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "hello123");
        System.out.println("Connection established......");
        //Creating the Statement object
        Statement stmt = con.createStatement();

        String query = "select count(*) from cropprops";
        //Executing the query
        ResultSet rs = stmt.executeQuery(query);
        //Retrieving the result
        rs.next();

        int length = rs.getInt(1);

        //hashmap for images of vegetables

        HashMap<String, String> imageMap = new HashMap<String, String>();


        ArrayList<Crop> cropsPossible = new ArrayList<>();




        for (int cropIter = 1; cropIter <= length; cropIter++) {




            Crop crop = cropRepository.findById(cropIter);
            String cropSeason = crop.getSeason();




            if ((crop.getN().isEmpty()) || (crop.getP().isEmpty()) || (crop.getK().isEmpty())) {

                cropsPossible.add(crop);
                //imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));

                System.out.println( crop.getCropName() + "crop season is " + cropSeason);
                System.out.println("crop season is equal to a" + (cropSeason.toString().contains("a")));

                //System.out.println("this crop is possible " + crop.getCropName() + "crop ID" + crop.getId());

                if ((cropSeason.equals("s")) || (cropSeason.contains("a") )){
                    System.out.println("this crop is possible " + crop.getCropName() + "crop ID " + crop.getId());
                 imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));
                }

            }

            else{



                if ((Integer.parseInt(ds.getN()) >= Integer.parseInt(crop.getN())) && (Integer.parseInt(ds.getK()) >= Integer.parseInt(crop.getK())) &&
                        (Integer.parseInt(ds.getP()) >= Integer.parseInt(crop.getP()))) {
                    System.out.println( crop.getCropName() + " crop season is " + crop.getSeason());

                    //System.out.println("this crop is possible " + crop.getCropName() + "crop ID" + crop.getId());


                    cropsPossible.add(crop);
                    //imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));

                    if (crop.getSeason().contains(currSeason) || crop.getSeason().contains("a") ){
                        System.out.println("this crop is possible " + crop.getCropName() + "crop ID " + crop.getId());
                        imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));}


                }
            }

            // Check the sunlight now





        }

        model.addAttribute("crops_possible", cropsPossible);
        model2.addAttribute("hm", imageMap);



        return "crops";


    }

}




