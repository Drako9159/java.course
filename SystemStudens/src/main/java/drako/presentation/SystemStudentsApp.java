package drako.presentation;

import drako.dao.StudentDAO;
import drako.domain.Student;

import java.util.Scanner;

public class SystemStudentsApp {
    public static void main(String[] args) {
        var out = false;
        var console = new Scanner(System.in);

        var studentDao = new StudentDAO();
        while (!out) {
            try {
                showMenu();
                out = runOptions(console, studentDao);
            } catch (Exception e) {
                System.out.println("Error in: " + e.getMessage());
            }
            System.out.println();

        }


    }

    private static void showMenu() {
        System.out.print("""
                *** Student Systen ***
                1. List students
                2. Search student
                3. Add student
                4. Modify student
                5. Delete student
                6. Out
                Select an option
                """);
    }

    private static boolean runOptions(Scanner console, StudentDAO studentDAO) {
        var option = Integer.parseInt(console.nextLine());
        var out = false;
        switch (option) {
            case 1 -> {
                System.out.println("List students...");
                var students = studentDAO.listStudents();
                students.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Input ID of student...");
                var idStudent = Integer.parseInt(console.nextLine());
                var student = new Student(idStudent);
                var finder = studentDAO.findStudentById(student);
                if (finder) {
                    System.out.println("Student is: " + student);
                } else {
                    System.out.println("Student not found: " + student);
                }
            }
            case 3 -> {
                System.out.println("Add new student...");
                System.out.print("Name: ");
                var name = console.nextLine();
                System.out.print("Lastname: ");
                var lastName = console.nextLine();
                System.out.print("Phone: ");
                var phone = console.nextLine();
                System.out.print("Email: ");
                var email = console.nextLine();
                var student = new Student(name, lastName, phone, email);
                var adder = studentDAO.addStudent(student);
                if (adder) {
                    System.out.println("Student added: " + student);
                } else {
                    System.out.println("Student not added: " + student);
                }

            }
            case 4 -> {
                System.out.println("Modify student...");
                System.out.print("Input id: ");
                var idStudent = Integer.parseInt(console.nextLine());
                System.out.print("Name: ");
                var name = console.nextLine();
                System.out.print("Lastname: ");
                var lastName = console.nextLine();
                System.out.print("Phone: ");
                var phone = console.nextLine();
                System.out.print("Email: ");
                var email = console.nextLine();
                var student = new Student(idStudent, name, lastName, phone, email);
                var modified = studentDAO.modifyStudent(student);
                if (modified) {
                    System.out.println("Student modified: " + student);
                } else {
                    System.out.println("Student not modified: " + student);
                }
            }
            case 5 -> {
                System.out.println("Delete an student...");
                System.out.println("Id student: ");
                var idStudent = Integer.parseInt(console.nextLine());
                var student = new Student(idStudent);
                var delete = studentDAO.deleteStudent(student);
                if (delete) {
                    System.out.println("Student deleted: " + student);
                } else {
                    System.out.println("Student is not deleted: " + student);
                }
            }
            case 6 -> {
                System.out.println("Bye");
                out = true;
            }
            default -> System.out.println("Invalid option");
        }
        return out;
    }
}