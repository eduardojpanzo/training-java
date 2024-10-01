package ao.eduardojpazo.tasktracker.main;

import java.util.Scanner;

import ao.eduardojpazo.tasktracker.manage.TaskManager;
import ao.eduardojpazo.tasktracker.model.Status;

public class App {
    public static void main(String[] args) throws Exception {
        CLITemplate.initialStructureCLI();
        TaskManager taskManager =  new TaskManager();
        Scanner reader = new Scanner(System.in);
        String[] parts = reader.nextLine().split(" ",3);
        String command = parts[0]; 
        String argumentNumeric = "";
        String argumentString = "";

        if (parts.length > 1) {
            try {
               Integer.parseInt(parts[1]);
               argumentNumeric = parts[1];

                if (parts.length > 2) {
                    argumentString = parts[2].replaceAll("^\"|\"$", "");
                }
            } catch (NumberFormatException e) {
               argumentString = parts[1].replaceAll("^\"|\"$", ""); 

               if (parts.length > 2 && parts[1].contains("\"")) {
                argumentString =  (parts[1] +" "+ parts[2]).replaceAll("^\"|\"$", "");
               }
            }
        }

        reader.close();

        switch (command) {
            case "add":
                if (argumentString.equals("")) {
                    System.out.println("Usage: add <description>");
                    return;
                }
                taskManager.addTask(argumentString);
                break;
            case "update":
                if (argumentNumeric.equals("") && argumentString.equals("")) {
                    System.out.println("Usage: update <id> <description>");
                    return;
                }
                taskManager.updateTask(argumentNumeric, argumentString);
                break;
            case "delete":
                if(argumentNumeric.equals("")){
                    System.out.println("Usage: add <description>");
                    return;
                }
                taskManager.deleteTask(argumentNumeric);
                break;
            case "mark-in-progress":
                if (argumentNumeric.equals("")) {
                    System.out.println("Usage: add <description>");
                    return;
                }
                taskManager.markInProgress(argumentNumeric);
                break;
            case "mark-done":
                if (argumentNumeric.equals("")) {
                    System.out.println("Usage: add <description>");
                    return;
                }
                taskManager.markDone(argumentNumeric);
                break;
            case "list":
                if (!argumentString.equals("")) {
                    Status status;
                    try {
                        status = Status.valueOf(argumentString.toUpperCase().replace("-", "_"));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status: " + argumentString);
                        return;
                    }
                    taskManager.listTasks(status.toString());
                } else{
                    taskManager.listTasks("All");
                }
                break;
            default:
                System.out.println("Commad not found, make sure is corect");
                break;
        }

        taskManager.saveTasks();
        
    }
}
