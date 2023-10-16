package drako.tasks.controller;

import drako.tasks.model.Task;
import drako.tasks.service.TaskService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class IndexController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, Integer> idTaskColumn;

    @FXML
    private TableColumn<Task, String> nameTaskColumn;

    @FXML
    private TableColumn<Task, String> personTaskColumn;

    @FXML
    private TableColumn<Task, String> statusTaskColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
