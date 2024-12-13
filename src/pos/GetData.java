/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos;

import java.sql.*;

/**
 *
 * @author CycloneTG
 */
public class GetData {
    
    private int staffId;
    private String staffName;
    private String position;
    private SqlConnector con = new SqlConnector();
    
    public GetData(SqlConnector con) {
        this.con = con;
    }

    public void tblStaff() {
        String query = "SELECT * FROM tblStaff";

        try {
            con.ConnectionDB("localhost:3306", "clothesshop", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void staffChecker(int staffId) {
        this.staffId = staffId;
        String query = "SELECT * FROM tblStaff WHERE id = ?";

        try (PreparedStatement pstmt = con.getConnection().prepareStatement(query)) {
            pstmt.setInt(1, staffId);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    position = rs.getString("position");
                    position = position.substring(0, 1).toUpperCase() + position.substring(1).toLowerCase();
                    staffName = rs.getString("first_name");
                    staffName = staffName.substring(0, 1).toUpperCase() + staffName.substring(1).toLowerCase();
                } else {
                    staffName = null;
                    position = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            staffName = null;
            position = null;
        }
    }
    
    public String getStaffName() {
        return staffName;
    }

    public String getPosition() {
        return position;
    }
}
