package MyLearningProject.restservice.climateService;

import MyLearningProject.restservice.ClimateRepository;
import MyLearningProject.restservice.models.Area;
import MyLearningProject.restservice.pincodeService.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ClimateServiceImpl implements ClimateService {

    @Autowired
    private ClimateRepository climateRepository;

    @Autowired
    private PincodeService pincodeService;


    @Override
    public Long findTempByLocation(int month, String parameter, String pincode) throws SQLException {


        Area area =  pincodeService.getArea(pincode);
        Long lat = area.getLat();
        Long lon = area.getLon();

        System.out.println("lat " + lat + "lon" + lon);

        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost/pincodes";
        Connection con = DriverManager.getConnection(mysqlUrl, "newuser", "password");
        Statement stmt = con.createStatement();
        String query = "select value from  nclimate where lat = '"+ lat +"' and lon = '"+ lon +"' and month = '"+ month +"' and parameter = '"+ parameter +"'";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        Long resultVal = null;

        while (rs.next()) {
            resultVal = rs.getLong("value");
        }

        con.close();
        System.out.println("Connection Closed....");
        return resultVal;
    }



}
