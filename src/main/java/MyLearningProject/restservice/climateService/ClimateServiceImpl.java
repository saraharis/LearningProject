package MyLearningProject.restservice.climateService;

import MyLearningProject.restservice.ClimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ClimateServiceImpl implements ClimateService {

    @Autowired
    private ClimateRepository climateRepository;

    @Override
    public Long findTempByLocation(Long lat, Long lon, int month, String parameter) throws SQLException {

        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection

        String mysqlUrl = "jdbc:mysql://localhost/pincodes";
        Connection con = DriverManager.getConnection(mysqlUrl, "newuser", "password");
        Statement stmt = con.createStatement();
        String query = "select * from climate where lat = lat and lon = lon and month = month and parameter = parameter";
        //Executing the query
        ResultSet rs = stmt.executeQuery(query);
        //Retrieving the result
        rs.next();

        Long resultVal = null;

        while (rs.next()) {

            Long latitude = rs.getLong("lat");
            Long longitude = rs.getLong("lon");
            int monthVar = rs.getInt("month");
            //System.out.println(latitude + ", " + longitude + ", " + monthVar);
            resultVal = rs.getLong("value");


        }

        con.close();
        System.out.println("Connection Closed....");
        return resultVal;
    }


}
