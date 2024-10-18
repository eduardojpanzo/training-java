package ao.eduardojpanzo.storebook.model;


import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
   private String id;
   private String selfLink;
   private String description; 
   private String title;

   private List<Author> authors;

   @JsonAlias("volumeInfo")
   private VolumeInfo volumeInfo;

   public Book () {}

   public Book (Book bookData) {
      id = bookData.getId();  
      selfLink = bookData.getSelfLink();  
      description = bookData.getVolumeInfo().getDescription();  
      title = bookData.getVolumeInfo().getTitle();  
      authors = bookData.getVolumeInfo().getAuthors().stream().map(a-> new Author(a)).collect(Collectors.toList());
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public List<Author> getAuthors() {
      return authors;
   }

   public void setAuthors(List<Author> authors) {
      this.authors = authors;
   }

   public String getSelfLink() {
      return selfLink;
   }

   public void setSelfLink(String selfLink) {
      this.selfLink = selfLink;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public VolumeInfo getVolumeInfo() {
      return volumeInfo;
   }

   public void setVolumeInfo(VolumeInfo volumeInfo) {
      this.volumeInfo = volumeInfo;
   }

   @Override
   public String toString() {
      return "Book [id=" + id + ", selfLink=" + selfLink + ", description=" + description + ", title=" + title
            + ", authors=" + authors + "] \n";
   }

  

   

   
}
