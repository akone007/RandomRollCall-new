package com.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Entity.Student;

public class RollCallSystem {
    private static final String SELECT_ALL_STUDENTS_SQL = "SELECT * FROM students";
    private static final String UPDATE_STUDENT_SQL = "UPDATE students SET called_times=?, answered_times=? WHERE id=?";

    public Student randomlySelectStudent() throws SQLException {
        List<Student> students = getAllStudents();
        int index = new Random().nextInt(students.size());
        return students.get(index);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/itheima", "root","123456");
    }

    private List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_STUDENTS_SQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int calledTimes = rs.getInt("called_times");
                int answeredTimes = rs.getInt("answered_times");
                int state = rs.getInt("state");
                students.add(new Student(id, name, calledTimes, answeredTimes,state));
            }
        }
        return students;
    }

        public void updateStudent(Student student) throws SQLException {
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(UPDATE_STUDENT_SQL)) {
                stmt.setInt(1, student.getCalledTimes());
                stmt.setInt(2, student.getAnsweredTimes());
                stmt.setInt(3, student.getId());
                stmt.executeUpdate();
            }
        }
}