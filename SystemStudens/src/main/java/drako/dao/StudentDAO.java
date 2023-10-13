package drako.dao;

import drako.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static drako.connection.Connect.getConnection;

public class StudentDAO {
    public List<Student> listStudents() {
        List<Student> students = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM student ORDER BY id_student";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var student = new Student();
                student.setIdStudent(rs.getInt("id_student"));
                student.setName(rs.getString("name"));
                student.setLastName(rs.getString("last_name"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
        } catch (Exception e) {
            System.out.println("Error in: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error in: " + e.getMessage());
            }
        }
        return students;
    }

    public boolean findStudentById(Student student) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM student WHERE id_student = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            rs = ps.executeQuery();
            if (rs.next()) {
                student.setName(rs.getString("name"));
                student.setLastName(rs.getString("last_name"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error in: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean addStudent(Student student) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "insert into student(name, last_name, phone, email) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error in add student: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error in: " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var studentDAO = new StudentDAO();
        System.out.println("List of students: ");
        List<Student> students = studentDAO.listStudents();
        //students.forEach(System.out::println);

        var student1 = new Student(1);
        System.out.println("Student before search: " + student1);
        var studentFind = studentDAO.findStudentById(student1);
        if (studentFind) {
            System.out.println("Search result: " + student1);
        } else {
            System.out.println("Student not found: " + student1.getIdStudent());
        }


    }
}
