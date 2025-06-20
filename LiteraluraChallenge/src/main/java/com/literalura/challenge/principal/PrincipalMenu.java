package com.literalura.challenge.principal;

import com.literalura.challenge.model.Author;
import com.literalura.challenge.model.Book;
import com.literalura.challenge.repository.AuthorRepository;
import com.literalura.challenge.repository.BookRepository;
import com.literalura.challenge.service.APIconsume;
import com.literalura.challenge.service.APIresponse;
import com.literalura.challenge.service.dataConverter;

import java.util.List;
import java.util.Scanner;

public class PrincipalMenu {
    private Scanner scan = new Scanner(System.in);
    private APIconsume api = new APIconsume();
    private dataConverter converter = new dataConverter();
    private final String BASE_URL = "https://gutendex.com/books";
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private final List<String> languages = List.of("es", "en", "fr", "pt");

    public PrincipalMenu(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        int userOption = -1;
        while (userOption != 0) {
            String menu = """
                    -------------------------------------
                    Introduce la opción que deseas: (1-5)
                    1 - Buscar libros por titulo.
                    2 - Buscar autor por nombre.
                    3 - Mostrar libros registrados.
                    4 - Mostrar autores registrados.
                    5 - Mostrar autores vivos en cierto año.
                    6 - Mostrar libros por idioma.
                    7 - Mostrar Top 10 Libros por descargas.
                    
                    0- Salir.
                    -------------------------------------
                    """;

            System.out.println(menu);
            userOption = scan.nextInt();
            scan.nextLine();

            switch (userOption) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    searchAuthorByName();
                    break;
                case 3:
                    showRegisteredBook();
                    break;
                case 4:
                    showRegisteredAuthors();
                    break;
                case 5:
                    showAuthorsByYear();
                    break;
                case 6:
                    showBooksByLanguage();
                    break;
                case 7:
                    showTop10Books();
                    break;
                case 0:
                    System.out.println();
                    break;
                default:
                    System.out.println("Introduce una opción correcta");
                    break;
            }
        }
    }

    private void searchByTitle(){
        System.out.println("Escribe el titulo del libro a buscar: ");
        String bookName = scan.nextLine().toLowerCase().replace(" ","+");
        String url = BASE_URL+"?search="+bookName;
        var json = APIconsume.getData(url);
        var data = converter.getData(json, APIresponse.class);
        if(data.count() != 0){
            Book book = new Book(data.results().get(0));
            var author = authorRepository.findByName(book.getAuthor().getName());
            if(author == null){
                authorRepository.save(book.getAuthor());
            }else{
                book.setAuthor(author);
            }
            bookRepository.save(book);
            System.out.println("Libro guardado:\n"+book);
        } else{
            System.out.println("El libro no fue encontrado");
        }


    }
    private void showRegisteredBook(){
        System.out.println("------- Libros Registrados ---------");
        var books = bookRepository.findAll();
        if(books.isEmpty()){
            System.out.println("No hay libros registrados");
        }else{
            books.stream().forEach(System.out::println);
        }
    }
    private void showRegisteredAuthors(){
        System.out.println("------- Autores Registrados ---------");
        var authors = authorRepository.findAll();
        if(authors.isEmpty()){
            System.out.println("No hay autores registrados...");
        }else{
            authors.stream().forEach(a -> System.out.println(a.toStringFull()));
        }

    }
    private void showAuthorsByYear(){
        System.out.println("Escriba el año deseado: ");
        Integer year = scan.nextInt();
        List<Author> authors = authorRepository.authorsAliveInYear(year);
        System.out.println("------- Autores Vivos en "+year+" ---------");
        if(authors.isEmpty()){
            System.out.println("No hay autores vivos en el año "+year+"...");
        }else{
            authors.stream().forEach(a -> System.out.println(a.toStringFull()));
        }

    }
    private void showBooksByLanguage(){
        System.out.println("Escriba la abreviación del idioma de los libros a buscar: ");
        System.out.println("""
                1- es | Español.
                2- en | Inglés.
                3- fr | Francés.
                4- pt | Portugués.
                """);
        String language = scan.nextLine().toLowerCase().replace(" ","");
        List<Book> books = bookRepository.booksByLanguage(language);
        if(languages.contains(language)){
            System.out.println("------- Libros por Idioma ---------");
            if(books.isEmpty()){
                System.out.println("No hay libros por el idioma buscado...");
            }else{
                books.stream().forEach(System.out::println);
            }
        }else{
            System.out.println("El idioma seleccionado no existe...");
        }

    }

    private void searchAuthorByName(){
        System.out.println("Escribe el nombre del autor a buscar: ");
        String authorName = scan.nextLine().toLowerCase();
        var author = authorRepository.findByNameContainsIgnoreCase(authorName);
        if(author == null){
            System.out.println("El autor no se ha encontrado...");
        }else{
            System.out.println(author.toStringFull());
        }
    }

    private void showTop10Books(){
        System.out.println("------- TOP 10 Libros por Descargas ---------");
        List<Book> books = bookRepository.getTop10Books();
        books.stream().forEach(System.out::println);
    }
}