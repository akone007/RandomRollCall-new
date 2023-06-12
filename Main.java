package Controller;

import com.DAO.ClearDatabase;
import com.DAO.ExportDatabaseContentToTextFile;
import com.Entity.Student;
import com.Service.CalculateAttendanceRate;
import com.Service.CalculateStudentStats;
import com.Service.CollCall;
import com.Service.SignIn;
import com.Util.Connecting;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel, buttonPanel, inputPanel, outputPanel;
    private JButton signInButton, attendanceButton, collCallButton, studentStatsButton, exportDataButton, exitButton, submitButton;
    //private JTextField inputField;
    private JTextArea outputTextArea;

    public Main() {
        // 设置窗口大小和位置
    	setName("随机签到和点名系统");
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // 创建主面板和按钮面板
        mainPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();

        // 创建按钮并添加到按钮面板
        signInButton = new JButton("签到");
        attendanceButton = new JButton("计算出勤率");
        collCallButton = new JButton("随机点名");
        studentStatsButton = new JButton("计算学生数据");
        exportDataButton = new JButton("导出数据库内容");
        exitButton = new JButton("退出程序");

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        buttonPanel.add(signInButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(attendanceButton, gbc);

        gbc.gridx = 2;
        buttonPanel.add(collCallButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(studentStatsButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(exportDataButton, gbc);

        gbc.gridx = 2;
        buttonPanel.add(exitButton, gbc);

        // 添加按钮面板到主面板中
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(10, 10, 0, 10);
        mainPanel.add(buttonPanel, gbc);

        // 创建输入面板和输出面板
        //inputPanel = new JPanel(new FlowLayout());
        outputPanel = new JPanel(new BorderLayout());

        // 创建输入框、提交按钮和输出标签，并添加到相应的面板中
        //inputField = new JTextField(20);
        //submitButton = new JButton("提交");
        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        //inputPanel.add(new JLabel("请输入："));
        //inputPanel.add(inputField);
        //inputPanel.add(submitButton);

        // 将输入面板和输出面板添加到主面板中
        //gbc.gridx = 0;
        //gbc.gridy = 1;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 1;
        //gbc.weightx = 1.0;
        //gbc.weighty = 0.0;
        //gbc.insets = new Insets(10, 10, 10, 10);
        //mainPanel.add(inputPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 10, 10);
        mainPanel.add(outputPanel, gbc);

        // 注册按钮监听器
        signInButton.addActionListener(this);
        attendanceButton.addActionListener(this);
        collCallButton.addActionListener(this);
        studentStatsButton.addActionListener(this);
        exportDataButton.addActionListener(this);
        exitButton.addActionListener(this);
        //submitButton.addActionListener(this);

        // 将主面板添加到窗口中
        add(mainPanel);

        // 设置窗口可见性
        setVisible(true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            // 调用签到方法
            try {
                List<Student> signedStudents = SignIn.run(outputTextArea);
                for (Student student : signedStudents) {
                    outputTextArea.append("签到成功！姓名：" + student.getName() + "\n");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "签到失败：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == attendanceButton) {
            // 调用计算出勤率方法
            CalculateAttendanceRate.calculateAttendanceRateAndOutput(outputTextArea);
            JOptionPane.showMessageDialog(this, "计算完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == collCallButton) {
            // 调用随机点名方法
            
            Student calledStudents = CollCall.collCall();;
			
            JOptionPane.showMessageDialog(this, "点名完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == studentStatsButton) {
            // 调用计算学生数据方法
            CalculateStudentStats calculator = new CalculateStudentStats();
            outputTextArea.append("学号\t姓名\t点名次数\t回答次数\t出勤状态\n");
            List<Student> students = calculator.calculateStudentStats(outputTextArea);
            for (Student student : students) {
                outputTextArea.append(student.getId() + "\t" + student.getName() + "\t" + student.getCalledTimes() +
                        "\t" + student.getAnsweredTimes() + "\t" + student.getState() + "%\n");
            }
            JOptionPane.showMessageDialog(this, "计算完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == exportDataButton) {
            // 调用导出数据库内容方法
            ExportDatabaseContentToTextFile.exportDatabaseContentToTextFile();
            JOptionPane.showMessageDialog(this, "导出完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == exitButton) {
            // 退出程序
            System.exit(0);
        } //else if (e.getSource() == submitButton) {
            // 处理提交按钮的动作
           // String inputText = inputField.getText();
            //outputTextArea.append(inputText + "\n");
            // 清空输入框中的文本
            //inputField.setText("");
        //}
    }

    public static void main(String[] args) {
        // 使用前清空数据库
        ClearDatabase.clearDatabase();

        // 将学生信息插入数据库
        List<Student> students = readStudentsFromFile();
        insertStudentsIntoDatabase(students);

        // 创建GUI窗口
        new Main();
    }

    private static List<Student> readStudentsFromFile() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int calledTimes = Integer.parseInt(parts[2]);
                int answeredTimes = Integer.parseInt(parts[3]);
                int state = Integer.parseInt(parts[4]);
                students.add(new Student(id, name, calledTimes, answeredTimes, state));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private static void insertStudentsIntoDatabase(List<Student> students) {
        try (Connection conn = Connecting.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (id,name,called_times,answered_times,state) VALUES (?, ?, ?, ?, ?)")) {
            for (Student student : students) {
                stmt.setInt(1, student.getId());
                stmt.setString(2, student.getName());
                stmt.setInt(3, student.getCalledTimes());
                stmt.setInt(4, student.getAnsweredTimes());
                stmt.setInt(5, student.getState());
                stmt.executeUpdate();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
   