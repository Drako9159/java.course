package drako.students;

import drako.students.model.Student;
import drako.students.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class StudentsApplication implements CommandLineRunner {

    @Autowired
    private StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentsApplication.class);

    String nl = System.lineSeparator();

    public static void main(String[] args) {
        logger.info("Initializing application...");
        SpringApplication.run(StudentsApplication.class, args);
        logger.info("Application out!");
    }

    // for console usage
    @Override
    public void run(String... args) throws Exception {
        logger.info(nl + "run method Spring..." + nl);
        var out = false;
        var console = new Scanner(System.in);
        while (!out) {

            showMenu();
            out = runOptions(console);
            logger.info(nl);

        }

    }

    private void showMenu() {
        logger.info("""
                *** System students ***
                1. List students
                2. Find student
                3. Add student
                4. Modify student
                5. Delete student
                6. Out
                Select an option:""");
    }

    private boolean runOptions(Scanner console) {
        var option = Integer.parseInt(console.nextLine());
        var out = false;
        switch (option) {
            case 1 -> {
                logger.info(nl + "List students: " + nl);
                List<Student> students = studentService.listStudents();
                students.forEach((student -> logger.info(student.toString() + nl)));
            }
            case 2 -> {
                logger.info("Find an student: ");
                var idStudent = Integer.parseInt(console.nextLine());
                Student student = studentService.findStudentById(idStudent);
                if (student != null) {
                    logger.info("Student found: " + student + nl);
                } else {
                    logger.info("Student not found: " + idStudent + nl);
                }
            }
            case 3 -> {
                logger.info("Add new student: " + nl);
                logger.info("Name: ");
                var name = console.nextLine();
                logger.info("Lastname: ");
                var lastName = console.nextLine();
                logger.info("Phone: ");
                var phone = console.nextLine();
                logger.info("Email: ");
                var email = console.nextLine();

                Student student = new Student();
                student.setName(name);
                student.setLastName(lastName);
                student.setPhone(phone);
                student.setEmail(email);

                studentService.addStudent(student);
                logger.info("Student is added: " + student + nl);
            }
            case 4 -> {
                logger.info("Modify student: " + nl);
                logger.info("Id student: ");
                var idStudent = Integer.parseInt(console.nextLine());
                Student student = studentService.findStudentById(idStudent);
                if (student != null) {

                    logger.info("Name: ");
                    var name = console.nextLine();
                    logger.info("Lastname: ");
                    var lastName = console.nextLine();
                    logger.info("Phone: ");
                    var phone = console.nextLine();
                    logger.info("Email: ");
                    var email = console.nextLine();

                    student.setName(name);
                    student.setLastName(lastName);
                    student.setPhone(phone);
                    student.setEmail(email);

                    studentService.addStudent(student);
                    logger.info("Student is modify: " + student + nl);
                } else {
                    logger.info("Student not found: " + idStudent + nl);
                }
            }
            case 5 -> {
                logger.info("Delete student: " + nl);
                logger.info("Id student: ");
                var idStudent = Integer.parseInt(console.nextLine());
                var student = studentService.findStudentById(idStudent);
                if (student != null) {
                    studentService.deleteStudent(student);
                    logger.info("Student is deleted: " + student + nl);
                } else {
                    logger.info("Student not found: " + idStudent);
                }
            }
            case 6 -> {
                logger.info("Bye" + nl + nl);
                out = true;
            }
            default -> logger.info("Invalid option: " + option + nl);
        }
        return out;
    }
}
