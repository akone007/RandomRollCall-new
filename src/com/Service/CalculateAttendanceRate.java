package com.Service;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Util.Connecting;
import javax.swing.JTextArea;

public class CalculateAttendanceRate extends Connecting {
    private static double attendanceRate;

    public static void calculateAttendanceRate() {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM students WHERE state = 1");
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            int presentCount = rs.getInt(1);

            try (PreparedStatement stmt2 = conn.prepareStatement("SELECT COUNT(*) FROM students");
                 ResultSet rs2 = stmt2.executeQuery()) {
                rs2.next();
                int totalCount = rs2.getInt(1);
                attendanceRate = (double) presentCount / totalCount;
            }

            // 将出勤率写入输出文件的最后一行
            String result = String.format("出勤率 = %d%%\n", (int) (attendanceRate * 100));
            try (PrintWriter writer = new PrintWriter("database_content.txt", "UTF-8")) {
                writer.print(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static double getAttendanceRate() {
        return attendanceRate;
    }

    public static void calculateAttendanceRateAndOutput(JTextArea outputTextArea) {
        calculateAttendanceRate();
        double attendanceRate = getAttendanceRate();
        outputTextArea.append(String.format("出勤率为：%.2f%%\n", attendanceRate * 100));
    }
}
