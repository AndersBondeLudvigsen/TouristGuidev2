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
        List<String> tagsList = new ArrayList<>();
        String SQL = "SELECT TDESCRIPTION FROM TAG;";
        Connection con = ConnectionManager.getConnection(db_url,uid,pwd);
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                String TDESCRIPTION = rs.getString("TDESCRIPTION");
                tagsList.add(TDESCRIPTION);
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return tagsList;
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
                ResultSet rs = psts.executeQuery();

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
        String SQLUPDATE = "UPDATE TOURISTATTRACTION SET ADESCRIPTION = ? WHERE ANAME = ?;";
        String SQLDELETE = "DELETE FROM TOURISTATTACTION_TAGS WHERE TOURISTID = (SELECT TOURISTID FROM TOURISTATTRACTION WHERE ANAME = ?); ";
        String SQLINSERT = "INSERT INTO TOURISTATTACTION_TAGS (TAGSID, TOURISTID) " +
                "SELECT tag.tagsid, touristattraction.touristid " +
                "FROM tag, touristattraction " +
                "WHERE tag.tdescription = ? AND touristattraction.aname = ?;";
        Connection con = ConnectionManager.getConnection(db_url, uid, pwd);
        try (PreparedStatement updateAttractionStmt = con.prepareStatement(SQLUPDATE);
             PreparedStatement deleteTagsStmt = con.prepareStatement(SQLDELETE);
             PreparedStatement insertTagsStmt = con.prepareStatement(SQLINSERT)) {

            updateAttractionStmt.setString(1, touristAttractionUpdated.getDescription());
            updateAttractionStmt.setString(2, touristAttractionUpdated.getName());
            updateAttractionStmt.executeUpdate();

            deleteTagsStmt.setString(1, touristAttractionUpdated.getName());
            deleteTagsStmt.executeUpdate();

            for (String tag : touristAttractionUpdated.getTags()) {
                insertTagsStmt.setString(1, tag);
                insertTagsStmt.setString(2, touristAttractionUpdated.getName());
                insertTagsStmt.executeUpdate();
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return touristAttractionUpdated;
    }

    public void addTouristAttraction (TouristAttraction touristAttraction){
        String SQL1 = "INSERT INTO TOURISTATTRACTION(ANAME, ADESCRIPTION) values (?,?);";
        String SQL2 = "INSERT INTO TOURISTATTACTION_TAGS (TAGSID, TOURISTID) " +
                "SELECT tag.tagsid, touristattraction.touristid " +
                "FROM tag, touristattraction " +
                "WHERE  tag.tdescription = ? " +
                "AND touristattraction.aname = ?;";
        Connection con = ConnectionManager.getConnection(db_url,uid,pwd);
        try (PreparedStatement pstmt1 = con.prepareStatement(SQL1);
        PreparedStatement pstmt2 = con.prepareStatement(SQL2)){
            pstmt1.setString(1,touristAttraction.getName());
            pstmt1.setString(2,touristAttraction.getDescription());
            pstmt1.executeUpdate();
            for (String tag:touristAttraction.getTags()) {
                pstmt2.setString(1, tag);
                pstmt2.setString(2, touristAttraction.getName());
                pstmt2.executeUpdate();
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }




    public void deleteTouristAttraction(String name) {
        String SQL1  = "DELETE FROM TOURISTATTACTION_TAGS WHERE TOURISTID = (SELECT TOURISTID FROM TOURISTATTRACTION WHERE ANAME = ?);";
        String SQL2 = "DELETE FROM TOURISTATTRACTION WHERE ANAME = ?;";
        Connection con = ConnectionManager.getConnection(db_url,uid,pwd);
        try (PreparedStatement psts1 = con.prepareStatement(SQL1);
                PreparedStatement psts2 = con.prepareStatement(SQL2)){
            psts1.setString(1,name);
            psts2.setString(1,name);
             psts1.executeUpdate();
             psts2.executeUpdate();
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
