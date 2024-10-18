package ao.eduardojpanzo.storebook.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Author {
   private String id;
   private String name;
   private List<Book> books;

   Author(){

   }

   Author(String name){
      this.name = name;
   }
   
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public List<Book> getBooks() {
      return books;
   }
   public void setBooks(List<Book> books) {
      this.books = books;
   }
   @Override
   public String toString() {
      return "Author [id=" + id + ", name=" + name +"]";
   }

   
   
}
