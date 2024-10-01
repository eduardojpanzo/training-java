package ao.eduardojpazo.tasktracker.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ao.eduardojpazo.tasktracker.manage.TaskManager;
import ao.eduardojpazo.tasktracker.model.Task;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("********************");
        // System.out.println("****Task-Tracker****");
        // System.out.println("********************");

        // Scanner reader = new Scanner(System.in);
        // String controller = "Y";
        // List<Task> taskList = new ArrayList<>();

        // while (controller.equalsIgnoreCase("y")) {
        //     System.out.printf("%n%n*****Actions*****%n");
        //     System.out.println("0 - Create new Task");
        //     System.out.println("1 - update the Task");
        //     System.out.println("2 - Create new Task");
        //     System.out.println("0 - Create new Task");
        //     String commad = reader.nextLine();


        //     System.out.println(taskList);
        //     System.out.printf("%n%n********************%n");
        //     System.out.println("Quer continuar [Y/N]");
        //     controller = reader.next();
        // }
        
        TaskManager nManager =  new TaskManager();

        nManager.listTasks("In ");
    }
}
