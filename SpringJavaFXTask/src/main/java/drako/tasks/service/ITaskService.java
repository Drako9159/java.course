package drako.tasks.service;

import drako.tasks.model.Task;

import java.util.List;

public interface ITaskService {

    public List<Task> listTasks();

    public Task findTaskById(Integer idTask);

    public void addTask(Task task);

    public void deleteTask(Task task);

}
