package ao.eduardojpanzo.storebook.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VolumeInfo {
   @JsonAlias("title")
   private String title;
   @JsonAlias("authors")
   private String description; 

   @JsonAlias("authors")
   private List<String> authors;

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public List<String> getAuthors() {
      return authors;
   }

   public void setAuthors(List<String> authors) {
      this.authors = authors;
   }

   @Override
   public String toString() {
      return "VolumeInfo [title=" + title + ", description=" + description + ", authors=" + authors + "]";
   }

   
}
