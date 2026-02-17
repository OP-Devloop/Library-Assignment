package se.iths.oscarp.libraryassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.oscarp.libraryassignment.model.Book;
import se.iths.oscarp.libraryassignment.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books";
    }

    @GetMapping("/new")
    public String showCreateForm() {
        return "create-book";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
        return "book";
    }

    @PostMapping("")
    public String createBook(@ModelAttribute Book book) {
        Book book1 = bookService.createBook(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        Book book2 = bookService.updateBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
