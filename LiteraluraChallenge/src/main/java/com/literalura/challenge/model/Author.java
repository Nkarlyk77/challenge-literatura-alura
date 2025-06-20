package com.literalura.challenge.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private Long birth_year;
    private Long death_year;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author(String name, Long birth_year, Long death_year) {
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }

    public Author(AuthorData authorData){
        this.name = authorData.name();
        this.birth_year = authorData.birth_year();
        this.death_year = authorData.death_year();
    }

    public Author(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Long birth_year) {
        this.birth_year = birth_year;
    }

    public Long getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Long death_year) {
        this.death_year = death_year;
    }

    @Override
    public String toString() {
        return "----Autor----\n" +
                "Nombre : " + name + '\n'+
                "----------";
    }

    public String toStringFull(){
        List<String> bookTitles = books.stream().map(Book::getTitle).toList();
        return "----Autor----\n" +
                "Nombre : " + name + '\n'+
                "Fecha de Nacimiento : " + birth_year + '\n'+
                "Fecha de Fallecimiento : " + death_year + '\n'+
                "Libros : " + bookTitles + '\n'+
                "----------";
    }
}
