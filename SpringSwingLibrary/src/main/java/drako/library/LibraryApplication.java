package drako.library;

import drako.library.view.BookForm;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {

        //SpringApplication.run(LibraryApplication.class, args);
        ConfigurableApplicationContext springContext = new SpringApplicationBuilder(LibraryApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        EventQueue.invokeLater(() -> {
            BookForm bookForm = springContext.getBean(BookForm.class);
            bookForm.setVisible(true);
        });
    }

}
