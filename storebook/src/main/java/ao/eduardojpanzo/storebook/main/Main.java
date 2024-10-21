package ao.eduardojpanzo.storebook.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import ao.eduardojpanzo.storebook.model.Book;
import ao.eduardojpanzo.storebook.model.ResultData;
import ao.eduardojpanzo.storebook.repository.BookRepository;
import ao.eduardojpanzo.storebook.service.ConvertToClass;
import ao.eduardojpanzo.storebook.service.RequestAPI;

public class Main {
   Scanner read = new Scanner(System.in);
   RequestAPI rApi =  new RequestAPI();
   ConvertToClass cToClass = new ConvertToClass();
   List<Book> books = new ArrayList<>();
   BookRepository repository;

   public Main (BookRepository repository) {
      this.repository = repository;
   }

   public void show() {
      System.out.println("--------------STORE BOOK--------------");
      System.out.println("Pesquise por livro para Armazer no App");
      var query = read.nextLine();
      getBookFromAPI(query);
      
      chooseBookId();
   }
   
   public void getBookFromAPI (String query) {
      try {
         var jsonResult = rApi.searchFromGoogleAPI(query);
         ResultData resultData = cToClass.getData(jsonResult, ResultData.class);
         resultData.items().stream().forEach(b-> {
            var newBook = new Book(b);
            books.add(newBook);
         });         
      } catch (Exception e) {
         System.out.println(e);
      }
   }
   
   public void chooseBookId(){
      System.out.println("--------------LIVROS ENCOTRADOS--------------");
      books.stream()
         .forEach(b-> System.out.println("ID: "+b.getId()+" Titulo: "+ b.getTitle()));
      System.out.println("Digite Id de um lovro para guardar na App");
      var bookId = read.nextLine();
      storeBook(bookId);
   }

   public void storeBook(String id){
     Optional<Book> book = books.stream()
      .filter(e->e.getId().toUpperCase().equals(id.toUpperCase())).findFirst();

      if (book.isPresent()) {
        
         return;
      }

      System.out.println("Id não encotrado!");
      
   }
   
}
