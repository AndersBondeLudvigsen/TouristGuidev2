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


    public TouristAttraction updateTouristAttraction(TouristAttraction touristAttractionUpdated) {
        int rows = 0;
        String SQL = "UPDATE TOURISTATTRACTION SET touristattraction = ? WHERE ID = ? ;";
        return null;
    }

    public void addTouristAttraction (TouristAttraction touristAttraction){}

    public void deleteTouristAttraction(String name){}

    public ArrayList<String> getTags(String name){ return null;}

    public List<TouristAttraction> getTouristAttractions() {
        List<TouristAttraction> touristAttractions = new ArrayList<>();
        String SQL = "SELECT touristattraction.aname, touristattraction.adescription, tag.tdescription " +
                "from touristattaction_tags" +
                " join touristattraction on touristattaction_tags.TOURISTID = touristattraction.TOURISTID" +
                " join tag on touristattaction_tags.TAGSID = tag.TAGSID;";
        Connection con = ConnectionManager.getConnection(db_url,uid,pwd);
        try (PreparedStatement psts = con.prepareStatement(SQL)){
            ResultSet rs = psts.executeQuery(SQL);

            String currentAname = "";
            TouristAttraction currentTouristAttraction = null;


            while(rs.next()) {
                String ANAME = rs.getString("ANAME");
                String ADESCRIPTION = rs.getString("ADESCRIPTION");
                String TDESCRIPTION = rs.getString("TDESCRIPTION");

                if (ANAME.equals(currentAname)){
                    currentTouristAttraction.addTag(TDESCRIPTION);
                } else {
                    currentTouristAttraction = new TouristAttraction(ANAME, ADESCRIPTION, new ArrayList<>(List.of(TDESCRIPTION)));
                    currentAname = ANAME;
                    touristAttractions.add(currentTouristAttraction);
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return touristAttractions;
        }
}
