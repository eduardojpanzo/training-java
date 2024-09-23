package ao.eduardojpazo.tasktracker.model;

import java.util.Date;

public class Task {
   private static int id;
   private String description;
   private boolean status;
   private Date createdAt;
   private Date updatedAt;

   public Task (String description){
      id += 1;
      this.description = description;
      this.status = false;
      this.createdAt = new Date();
      this.updatedAt = new Date();
   }

   public Task (){
      id += 1;
      this.description = "";
      this.status = false;
      this.createdAt = new Date();
      this.updatedAt = new Date();
   }

   public static int getId() {
      return id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isStatus() {
      return status;
   }
   
   public void setStatus(boolean status) {
      this.status = status;
   }

   public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
   }

   public Date getCreatedAt() {
      return createdAt;
   }

   public Date getUpdatedAt() {
      return updatedAt;
   }

   
}
