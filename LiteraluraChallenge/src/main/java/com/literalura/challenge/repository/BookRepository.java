package com.literalura.challenge.repository;

import com.literalura.challenge.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleContainsIgnoreCase(String bookname);
    @Query("SELECT b FROM Book b JOIN b.languages l WHERE l = :language")
    List<Book> booksByLanguage(String language);
    @Query("SELECT b FROM Book b ORDER BY b.download_count DESC LIMIT 10")
    List<Book> getTop10Books();
}
