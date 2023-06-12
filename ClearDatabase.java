package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Util.Connecting;

public class ClearDatabase {

    public static void clearDatabase() {
        try (Connection conn = Connecting.getConnection(); PreparedStatement pstmt = conn.prepareStatement("DELETE FROM students")) {
            pstmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
