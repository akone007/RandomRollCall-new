package com.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.Entity.Student;

public class RandomRollCall {
    private int n;  // 最多点名n个人
    private Connection conn;
    private List<Student> students;
    private List<Student> callList;
    private int unansweredCount;

    public RandomRollCall(int n) {
        this.n = n;
        this.students = new ArrayList<>();
        this.callList = new ArrayList<>();
        this.unansweredCount = 0;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itheima", "root", "123456");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void rollCall() {
        // 从数据库中获取签到成功的学生
        getSignedStudents();

        while (true) {
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(null, "所有同学均已回答，点名结束。", "提示", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            // 随机选择一个学生进行点名
            Student student = getRandomStudent();
            callList.add(student);
            updateCalledTimes(student);

            // 判断是否回答正确
            if (!answerQuestion(student)) {
                unansweredCount++;
                JOptionPane.showMessageDialog(null, "回答错误，当前未回答出问题的人数 ： " + unansweredCount, "提示", JOptionPane.INFORMATION_MESSAGE);
                if (unansweredCount == n) {
                    JOptionPane.showMessageDialog(null, "未回答出来的次数已经到达" + n + "个，接下来从回答出来的同学中随机点名", "提示", JOptionPane.INFORMATION_MESSAGE);
                    getAnsweredStudents();
                    unansweredCount = 0;
                }
            } else {
                unansweredCount = 0;
            }
        }
    }

    private void getSignedStudents() {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE state=1")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int calledTimes = rs.getInt("called_times");
                int answeredTimes = rs.getInt("answered_times");
                int state = rs.getInt("state");
                students.add(new Student(id, name, calledTimes, answeredTimes, state));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private Student getRandomStudent() {
        return students.get(new Random().nextInt(students.size()));
    }

    private void updateCalledTimes(Student student) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE students SET called_times=? WHERE id=?")) {
            stmt.setInt(1, student.getCalledTimes() + 1);
            stmt.setInt(2, student.getId());
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private boolean answerQuestion(Student student) {
        int answer = Integer.parseInt(JOptionPane.showInputDialog(null, "随机到的同学：" + student.getName() + "\n请回答当前问题\n是否回答正确？（0表示不正确，1表示正确）：", "提示", JOptionPane.QUESTION_MESSAGE));
        if (answer == 1) {
            updateAnsweredTimes(student);
            JOptionPane.showMessageDialog(null, "回答正确！", "提示", JOptionPane.INFORMATION_MESSAGE);
            students.remove(student); // 从列表中移除该学生
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "回答错误。", "提示", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private void updateAnsweredTimes(Student student) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE students SET answered_times=? WHERE id=?")) {
            stmt.setInt(1, student.getAnsweredTimes() + 1);
            stmt.setInt(2, student.getId());
            stmt.executeUpdate();

            // 更新学生状态，但不删除数据库中的记录
            student.setState(0);
            try (PreparedStatement stmt2 = conn.prepareStatement("UPDATE students SET state=0 WHERE id=?")) {
            	stmt2.setInt(1, student.getId());
            	stmt2.executeUpdate();
            	} catch (SQLException se) {
            	se.printStackTrace();
            	}
            	} catch (SQLException se) {
            	se.printStackTrace();
            	}
            	}

            	private void getAnsweredStudents() {
            	    students.clear();
            	    try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE answered_times>0")) {
            	        ResultSet rs = stmt.executeQuery();
            	        while (rs.next()) {
            	            int id = rs.getInt("id");
            	            String name = rs.getString("name");
            	            int calledTimes = rs.getInt("called_times");
            	            int answeredTimes = rs.getInt("answered_times");
            	            int state = rs.getInt("state");
            	            students.add(new Student(id, name, calledTimes, answeredTimes, state));
            	        }
            	    } catch (SQLException se) {
            	        se.printStackTrace();
            	    }
            	}

            	public List<Student> getCallList() {
            	    return callList;
            	}

            	public int getUnansweredCount() {
            	    return unansweredCount;
            	}

            	public void setUnansweredCount(int unansweredCount) {
            	    this.unansweredCount = unansweredCount;
            	}
            	}