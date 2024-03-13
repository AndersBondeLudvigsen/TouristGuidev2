package com.example.touristguidev2.repository;

import com.example.touristguidev2.model.TouristAttraction;
import com.example.touristguidev2.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristGuideRepositoryDB {

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    public List<String> getTagsList() {
        return null;
    }

    public TouristAttraction getTouristAttraction(String name){
        TouristAttraction touristAttraction = null;
        try (Connection con = ConnectionManager.getConnection(db_url, uid, pwd)){
        String SQL = "SELECT * FROM touristattraction WHERE aname = ? ;";
        PreparedStatement pstmt = con.prepareStatement(SQL);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String NAME = rs.getString("ANAME");
            String description = rs.getString("ADESCRIPTION");
            touristAttraction = new TouristAttraction(name, description);
        }
        return touristAttraction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTouristAttraction(TouristAttraction touristAttractionUpdated) {}

    public void addTouristAttraction (TouristAttraction touristAttraction){}

    public void deleteTouristAttraction(String name){}

    public ArrayList<String> getTags(String name){ return null;}

    public List<TouristAttraction> getTouristAttractions() {
        List<TouristAttraction> touristAttractions = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection(db_url,uid,pwd)){
            String SQL = "SELECT * FROM touristattraction;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                String name = rs.getString(2);
                String description = rs.getString(3);
                touristAttractions.add(new TouristAttraction(name,description));
            }
            return touristAttractions;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        }
}
