package ao.eduardojpanzo.storebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.eduardojpanzo.storebook.model.Book;

public interface BookRepository  extends JpaRepository<Book,Long>{
}
