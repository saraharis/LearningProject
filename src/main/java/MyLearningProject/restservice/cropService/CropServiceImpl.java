package MyLearningProject.restservice.cropService;

import MyLearningProject.restservice.CropRepository;
import MyLearningProject.restservice.models.DistrictSoil;
import MyLearningProject.restservice.models.Crop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;

@Component

public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepository cropRepository;

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

            System.out.println(cropIter);


            Crop crop = cropRepository.findById(cropIter);
            String cropSeason = crop.getSeason();

            System.out.println("print2  " + length + cropSeason);


            if ((crop.getN().isEmpty()) || (crop.getP().isEmpty()) || (crop.getK().isEmpty())) {
                System.out.println("print7  " + crop.getN());
                if ((cropSeason.contains(season)) || (cropSeason.contains("a"))) {
                    //System.out.println("this crop is possible " + crop.getCropName() + "crop ID " + crop.getId());
                    System.out.println("print7  " + crop.getN());
                    imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));

                    System.out.println("print3  " + cropIter);
                }

            } else {

                if ((Integer.parseInt(ds.getN()) >= Integer.parseInt(crop.getN())) && (Integer.parseInt(ds.getK()) >= Integer.parseInt(crop.getK())) &&
                        (Integer.parseInt(ds.getP()) >= Integer.parseInt(crop.getP()))) {

                    if (crop.getSeason().contains(season) || crop.getSeason().contains("a")) {
                        //System.out.println("this crop is possible " + crop.getCropName() + "crop ID " + crop.getId());
                        imageMap.put(crop.getCropName(), crop.getCropName().concat(".jpg"));

                        System.out.println("print4  " + cropIter);
                    }


                }
            }
        }

        con.close();
        System.out.println("Connection Closed.....");

        return imageMap;

    }

}
