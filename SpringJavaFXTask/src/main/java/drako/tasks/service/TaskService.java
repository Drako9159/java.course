package drako.tasks.service;

import drako.tasks.model.Task;
import drako.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll();

    }

    @Override
    public Task findTaskById(Integer idTask) {
        return taskRepository.findById(idTask).orElse(null);
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
