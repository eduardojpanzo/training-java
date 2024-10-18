package ao.eduardojpanzo.storebook.main;

import java.util.Scanner;

public class Main {
   Scanner read = new Scanner(System.in);

   public void show() {
      System.out.println("--------------STORE BOOK--------------");
      System.out.println("Pesquise por livro para Armazer no App");
      var query = read.nextLine();
      getBookFromAPI(query);
   }

   public void getBookFromAPI (String query) {
      var result = "";

      System.out.println(result);
   }
   
}
