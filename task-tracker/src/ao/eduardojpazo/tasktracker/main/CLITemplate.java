package ao.eduardojpazo.tasktracker.main;

public class CLITemplate {
   
   public static void initialStructureCLI (){
      System.out.println(
         """
         ---------------------------------Task Tracker---------------------------------
         $ add <desc>            - To Create new Task
         $ update <id> <desc>    - To update the Task
         $ delete <id>           - To delete the Task
         $ mark-in-progress <id> - To mark Task in progress
         $ mark-done <id>        - To mark task done
         $ list                  - To list all tasks
         $ list <status>         - To list some taks. status: done | todo | in-progress
         ------------------------------------------------------------------------------"""
      );
     

   }

   public static void parseCommand(String input){
      
   }
}
