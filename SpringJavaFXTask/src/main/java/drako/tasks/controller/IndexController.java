package drako.tasks.controller;

import drako.tasks.model.Task;
import drako.tasks.service.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    private TextField nameTaskText;

    @FXML
    private TextField personTaskText;

    @FXML
    private TextField statusTaskText;

    private Integer idTaskInside;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configColumns();
        listTasks();
    }

    private void configColumns() {
        idTaskColumn.setCellValueFactory(new PropertyValueFactory<>("idTask"));
        nameTaskColumn.setCellValueFactory(new PropertyValueFactory<>("nameTask"));
        personTaskColumn.setCellValueFactory(new PropertyValueFactory<>("person"));
        statusTaskColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void listTasks() {
        logger.info("List tasks...");
        taskList.clear();
        taskList.addAll(taskService.listTasks());
        taskTable.setItems(taskList);
    }

    public void addTask() {
        if (nameTaskColumn.getText().isEmpty()) {
            showMessage("Validation Error", "Input task");
            nameTaskText.requestFocus();
            return;
        } else {
            var task = new Task();
            collectorDataForm(task);
            task.setIdTask(null);
            taskService.addTask(task);
            showMessage("Information", "Task added");
            cleanForm();
            listTasks();
        }
    }

    public void chargeTableForm() {
        var task = taskTable.getSelectionModel().getSelectedItem();
        if (task != null) {
            idTaskInside = task.getIdTask();
            nameTaskText.setText(task.getNameTask());
            personTaskText.setText(task.getPerson());
            statusTaskText.setText(task.getStatus());
        }
    }

    public void modifyTask() {
        if (idTaskInside == null) {
            showMessage("Information", "Select a task");
            return;
        }
        if (nameTaskText.getText().isEmpty()) {
            showMessage("Validation error", "Input task name");
            nameTaskText.requestFocus();
            return;
        }
        var task = new Task();
        collectorDataForm(task);
        //task.setIdTask(null);
        taskService.addTask(task);
        showMessage("Information", "Task modified");
        cleanForm();
        listTasks();

    }

    public void deleteTask() {
        var task = taskTable.getSelectionModel().getSelectedItem();
        if (task != null) {
            logger.info("Item to delete: " + task.toString());
            taskService.deleteTask(task);
            showMessage("Information", "Task deleted: " + task.getIdTask());
            cleanForm();
            listTasks();
        } else {
            showMessage("Error", "Task not is selected");
        }

    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void collectorDataForm(Task task) {
        if (idTaskInside != null) {
            task.setIdTask(idTaskInside);
        }
        task.setNameTask(nameTaskText.getText());
        task.setPerson(personTaskText.getText());
        task.setStatus(statusTaskText.getText());

    }

    public void cleanForm() {
        idTaskInside = null;
        nameTaskText.clear();
        personTaskText.clear();
        statusTaskText.clear();

    }

}
