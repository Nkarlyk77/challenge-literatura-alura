package com.literalura.challenge.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private long download_count;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_language", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language")
    private List<String> languages;

    public Book(Long id, String title, long download_count, Author author, List<String> languages) {
        this.id = id;
        this.title = title;
        this.download_count = download_count;
        this.author = author;
        this.languages = languages;
    }

    public Book(){}

    public Book(BookData book) {
        this.languages = book.languages();
        this.title = book.title();
        this.download_count = book.download_count();
        this.author = new Author(book.authors().get(0));
    }

    public long getDownload_count() {
        return download_count;
    }

    public void setDownload_count(long download_count) {
        this.download_count = download_count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "----LIBRO----\n"+
                "Titulo: "+title+"\n"+
                "Autor(es): \n"+author+"\n"+
                "Idiomas: "+languages+"\n"+
                "Descargas: "+download_count+"\n"+
                "------------\n";
    }
}
