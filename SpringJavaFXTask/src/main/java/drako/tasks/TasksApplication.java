package drako.tasks;

import drako.tasks.presentation.TaskSystemFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

    public static void main(String[] args) {
        //SpringApplication.run(TasksApplication.class, args);
        Application.launch(TaskSystemFx.class, args);

    }

}
