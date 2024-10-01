package ao.eduardojpazo.tasktracker.manage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ao.eduardojpazo.tasktracker.model.Task;

public class TaskManager {
    private final Path FILE_PATH = Path.of("tasks.json");
    private List<Task> tasks;


    public TaskManager(){
        this.tasks = loadTasks();
    }

    /* load json file */
    private List<Task> loadTasks(){
        List<Task> stored_tasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)){
            return new ArrayList<>();
        }

        try {
            var jsonContent = Files.readString(FILE_PATH);
            String[] taskList = jsonContent.replace("[", "").replace("]", "").split("},");
            
            for (String task : taskList){
                String taskJson =  task.trim();

                if (!taskJson.endsWith("}")){
                    taskJson = taskJson + "}";
                    stored_tasks.add(Task.fromJson(taskJson));
                } else {
                    stored_tasks.add(Task.fromJson(taskJson));
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return stored_tasks;
    }

    public void saveTasks(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("[\n");

        for (int i = 0; i < tasks.size(); i++){
            strBuilder.append(tasks.get(i).toJson());
            if (i < tasks.size() - 1){
                strBuilder.append(",\n");
            }
        }
        strBuilder.append("\n]");
        String jsonContent = strBuilder.toString();

        try {
            Files.writeString(FILE_PATH, jsonContent);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addTask(String description){
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Task added successfully (ID: " + newTask.getId() + ")");
    }

    public void updateTask(String id, String newDescription){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
        task.setDescription(newDescription);
        System.out.println("Task updated successfully (ID: " + task.getId() + ")");
    }

    public void deleteTask(String id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
        tasks.remove(task);
        System.out.println("Task deleted successfully (ID: " + task.getId() + ")");
    }

    public void markInProgress(String id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
        task.markInProgress();
        System.out.println("Task marked as inprogress (ID: " + task.getId() + ")");
    }

    public void markDone(String id){
        Task task = findTask(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found!"));
        task.markDone();
        System.out.println("Task marked as done (ID: " + task.getId() + ")");
    }

    public void listTasks(String type){
        for (Task task : tasks){
            String status = task.getStatus().toString().strip();
            if (type.equals("All") || status.equals(type)){
                System.out.println(task.toString());
            }
        }
    }

    public Optional<Task> findTask(String id) {
        return tasks.stream().filter((task) -> task.getId() == Integer.parseInt(id)).findFirst();
    }

}
