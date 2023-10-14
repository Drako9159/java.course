package drako.students.service;

import drako.students.model.Student;
import drako.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {


    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Integer idStudent) {
        return studentRepository.findById(idStudent).orElse(null);
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}
