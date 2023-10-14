package drako.students.service;

import drako.students.model.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> listStudents();

    public Student findStudentById(Integer idStudent);

    public void addStudent(Student student);

    public void deleteStudent(Student student);
}
