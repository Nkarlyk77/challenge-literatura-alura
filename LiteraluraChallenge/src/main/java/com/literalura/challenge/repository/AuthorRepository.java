package com.literalura.challenge.repository;

import com.literalura.challenge.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
    Author findByNameContainsIgnoreCase(String name);
    @Query("SELECT a FROM Author a WHERE a.birth_year < :year AND (a.death_year IS NULL OR a.death_year > :year)")
    List<Author> authorsAliveInYear(Integer year);
}
