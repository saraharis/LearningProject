package MyLearningProject.restservice.cropService;

import MyLearningProject.restservice.CropRepository;
import MyLearningProject.restservice.climateService.ClimateService;
import MyLearningProject.restservice.models.DistrictSoil;
import MyLearningProject.restservice.models.Crop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component

public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepository cropRepository;
    @Autowired
    ClimateService climateService;

    @Override
    public HashMap<String, String> getImageMap(List<Crop> finalCropList){

        HashMap<String, String> imageMap = new HashMap<String, String>();

        for( Crop crop: finalCropList){
            imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));
        }

    return imageMap;
    }

    public List<Crop> findCropsByPh(Long ph){
        List<Crop>listCropsByPh = new ArrayList<>();

        for(Crop crop: cropRepository.findAll()){
            if(crop.getPh_min() == 0){
                listCropsByPh.add(crop);
                System.out.println("crops for this ph are " + crop.getCropName());
            }
            if((ph>= crop.getPh_min()) && (ph<= crop.getPh_max())){
                listCropsByPh.add(crop);
                System.out.println("crops for this ph are " + crop.getCropName());
            }
        }

        return listCropsByPh;
    }



    @Override

    public List<Crop> findCropsByTemp(Long temp){

        List<Crop> listCropsByTemp = new ArrayList<>();
        for(Crop crop : cropRepository.findAll()){

            if(temp<= crop.getT_max() && temp>= crop.getT_min()){
                listCropsByTemp.add(crop);
                System.out.println("crops for this temp are " + crop.getCropName());

            }
        }

        return listCropsByTemp;
    }
    @Override

    public List<Crop> findEmpty() throws SQLException {

        List<Crop> listCropsNull = new ArrayList<>();

        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost/pincodes";
        Connection con = DriverManager.getConnection(mysqlUrl, "newuser", "password");
        System.out.println("Connection established......");
        Statement stmt = con.createStatement();
        String nullQuery = "select id from ncropprops where 0 in (n,p,k)";
        System.out.println("error not here");
        ResultSet rsNull = stmt.executeQuery(nullQuery);
        rsNull.next();

        while(rsNull.next()){
            Crop crop = cropRepository.findById(rsNull.getInt("id"));
            listCropsNull.add(crop);
            System.out.println("Crop soil null val " + crop.getCropName());
        }

        con.close();
        System.out.println("connection closed...");


    return listCropsNull;
    }

    @Override
    public List<Crop> findCropsBySoil(DistrictSoil ds) throws SQLException {

        List<Crop> listCropsPossibleBySoil = new ArrayList<>();

        for(Crop crop : cropRepository.findAll()){


            if (((ds.getN()) >= crop.getN()) && ((ds.getK()) >= (crop.getK())) &&
                    ((ds.getP()) >= (crop.getP()))) {

                listCropsPossibleBySoil.add(crop);
                System.out.println("crop possible by soil is " + crop.getCropName());
            }


        }

        return listCropsPossibleBySoil;
    }

    @Override
    public List<Crop> findCropsBySeason(String season){

        List<Crop> cropsBySeason = new ArrayList<>();
        for( Crop crop : cropRepository.findAll()){

            if (crop.getSeason().contains(season) || crop.getSeason().contains("a")) {
                cropsBySeason.add(crop);
                System.out.println("crops for this season " + crop.getCropName());
            }


        }

        return cropsBySeason;
    }


    @Override

    public HashMap<String, String> findSuitableCrops(DistrictSoil ds, String season) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/pincodes";
        //Connection con = DriverManager.getConnection(mysqlUrl, "root", "hello123");
        Connection con = DriverManager.getConnection(mysqlUrl, "newuser", "password");
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

        for (int cropIter = 1; cropIter <= length; cropIter++) {

            Crop crop = cropRepository.findById(cropIter);
            String cropSeason = crop.getSeason();



            if ((crop.getN() == 0) || (crop.getP()== 0 ) || (crop.getK() == 0)) {

                if ((cropSeason.contains(season)) || (cropSeason.contains("a"))) {

                    imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));

                }

            } else {

                if (ds.getN() >= crop.getN() && (ds.getK()) >=(crop.getK()) &&
                        ds.getP() >= crop.getP()) {

                    if (crop.getSeason().contains(season) || crop.getSeason().contains("a")) {

                        imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));

                    }


                }
            }
        }

        con.close();
        System.out.println("Connection Closed.....");

        return imageMap;

    }

}
