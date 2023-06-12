package com.DAO;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Util.Connecting;

public class ExportDatabaseContentToTextFile extends Connecting{
	
	public static void exportDatabaseContentToTextFile() {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students")) {
            ResultSet rs = stmt.executeQuery();

            List<String> studentInfoList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                int calledTimes = rs.getInt("called_times");
                int answeredTimes = rs.getInt("answered_times");
                int state = rs.getInt("state");

                double successRate = 0.0;
                if (calledTimes != 0) {
                    successRate = ((double)answeredTimes / calledTimes) * 100;
                }

                String studentInfo = String.format("%s：是否签到成功=%d, 被点名次数=%d, 回答正确次数=%d, 成功回答比率=%.2f%%\n",
                        name, state, calledTimes, answeredTimes, successRate);
                studentInfoList.add(studentInfo);
            }

            StringBuilder sb = new StringBuilder();
            for (String s : studentInfoList) {
                sb.append(s);
            }
            String allStudentInfos = sb.toString();

            try (PrintWriter writer = new PrintWriter("database_content.txt", "UTF-8")) {
                writer.print(allStudentInfos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("已成功导出数据库内容到文件database_content.txt");
        } catch (SQLException se) {
            se.printStackTrace();
        }
        
    }
}
