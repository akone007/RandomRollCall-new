package com.Service;

import com.Entity.Student;
import com.Util.Connecting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class CalculateStudentStats extends Connecting {
    public CalculateStudentStats() {}

    public List<Student> calculateStudentStats(JTextArea outputTextArea) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students")) {
            ResultSet rs = stmt.executeQuery();
            outputTextArea.append("学号\t姓名\t点名次数\t回答次数\t成功率\n");
            while (rs.next()) {
                int calledTimes = rs.getInt("called_times");
                int answeredTimes = rs.getInt("answered_times");
                int successRate = 0;
                if (calledTimes != 0) {
                    successRate = (int)(((double)answeredTimes / calledTimes) * 100);
                }
                outputTextArea.append(rs.getString("id") + "\t" + rs.getString("name") + "\t" + calledTimes +
                        "\t" + answeredTimes + "\t" + successRate + "%\n");
                students.add(new Student(rs.getInt("id"), rs.getString("name"), calledTimes, answeredTimes, successRate));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return students;
    }
}
