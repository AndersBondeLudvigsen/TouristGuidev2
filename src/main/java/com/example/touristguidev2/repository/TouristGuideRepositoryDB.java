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
            String SQL = "SELECT touristattraction.aname, touristattraction.adescription, tag.tdescription " +
                    "from touristattaction_tags" +
                    " join touristattraction on touristattaction_tags.TOURISTID = touristattraction.TOURISTID" +
                    " join tag on touristattaction_tags.TAGSID = tag.TAGSID where touristattraction.aname = ?;";

            Connection con = ConnectionManager.getConnection(db_url,uid,pwd);
            try (PreparedStatement psts = con.prepareStatement(SQL)){
                psts.setString(1, name);
                ResultSet rs = psts.executeQuery(SQL);

                String currentAname = "";

                while(rs.next()) {
                    String ANAME = rs.getString("ANAME");
                    String ADESCRIPTION = rs.getString("ADESCRIPTION");
                    String TDESCRIPTION = rs.getString("TDESCRIPTION");

                    if (ANAME.equals(currentAname)){
                        touristAttraction.addTag(TDESCRIPTION);
                    } else {
                        touristAttraction = new TouristAttraction(ANAME, ADESCRIPTION, new ArrayList<>(List.of(TDESCRIPTION)));
                        currentAname = ANAME;
                    }
                }
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
            return touristAttraction;
        }


    public TouristAttraction updateTouristAttraction(TouristAttraction touristAttractionUpdated) {
        int rows = 0;
        String SQL = "UPDATE TOURISTATTRACTION SET touristattraction = ? WHERE ID = ? ;";
        return null;
    }

    public void addTouristAttraction (TouristAttraction touristAttraction){


    }




    public void deleteTouristAttraction(String name) {
        String SQL1  = "DELETE FROM TOURISTATTACTION_TAGS WHERE TOURISTID = (SELECT TOURISTID FROM TOURISTATTRACTION WHERE ANAME = ?);";
        String SQL2 = "DELETE FROM TOURISTATTRACTION WHERE ANAME = ?;";
        Connection con = ConnectionManager.getConnection(db_url,uid,pwd);
        try (PreparedStatement psts1 = con.prepareStatement(SQL1);
                PreparedStatement psts2 = con.prepareStatement(SQL2)){
            psts1.setString(1,name);
            psts2.setString(1,name);
            int rowsAffected1 = psts1.executeUpdate();
            int rowsAffected2 = psts2.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getTags(String name){
        ArrayList<String> tags = new ArrayList<>();
        String SQL = "SELECT tag.tdescription " +
                "from touristattaction_tags" +
                " join touristattraction on touristattaction_tags.TOURISTID = touristattraction.TOURISTID" +
                " join tag on touristattaction_tags.TAGSID = tag.TAGSID " +
                "WHERE touristattraction.aname = ?;";
        Connection con = ConnectionManager.getConnection(db_url,uid,pwd);
        try (PreparedStatement psts = con.prepareStatement(SQL)){
        psts.setString(1, name);
        ResultSet rs = psts.executeQuery();

        while(rs.next()){
            String TDESCRIPTION = rs.getString("TDESCRIPTION");
            tags.add(TDESCRIPTION);
        }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tags;
    }

    public List<TouristAttraction> getTouristAttractions() {
        List<TouristAttraction> touristAttractions = new ArrayList<>();
        String SQL = "SELECT touristattraction.aname, touristattraction.adescription, tag.tdescription " +
                "from touristattaction_tags" +
                " join touristattraction on touristattaction_tags.TOURISTID = touristattraction.TOURISTID" +
                " join tag on touristattaction_tags.TAGSID = tag.TAGSID;";
        Connection con = ConnectionManager.getConnection(db_url,uid,pwd);
        try (Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery(SQL);

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
