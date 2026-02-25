package se.iths.oscarp.libraryassignment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.oscarp.libraryassignment.exception.BookNotFoundException;
import se.iths.oscarp.libraryassignment.model.Book;
import se.iths.oscarp.libraryassignment.repository.BookRepository;
import se.iths.oscarp.libraryassignment.validator.BookValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookValidator mockBookValidator;
    @Mock
    private BookRepository mockBookRepository;

    @InjectMocks
    private BookService testBookService;


    @Test
    void getAll() {
        //when we call service.getAll wee should see that book Repository was called.
        //we are using a mocked bookRepository so we decide its behavior since it's not important.
        //we are only concerned with bookService
        Book book = new Book();
        Book book1 = new Book();
        when(mockBookRepository.findAll()).thenReturn(List.of(book, book1));

        List<Book> expected = List.of(book, book1);

        assertEquals(expected, testBookService.getAll()); //control if the method in service calls the method in repository
        verify(mockBookRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Book book = new Book("Igelkottens elegans", "Muriel Barbery", 2006, "Litterär skönlitteratur");
        when(mockBookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = testBookService.getById(1L);

        assertNotNull(result);
        assertEquals("Igelkottens elegans", result.getTitle());
    }

    @Test
    void findById_ThrowsException() {
        when(mockBookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> testBookService.getById(1L));
    }

    @Test
    void save_Validation_andSave() {
        Book book = new Book("Igelkottens elegans", "Muriel Barbery", 2006, "Litterär skönlitteratur");
        when(mockBookRepository.save(book)).thenReturn(book);

        Book result = testBookService.save(book);

        verify(mockBookValidator, times(1)).validate(book);
        verify(mockBookRepository, times(1)).save(book);
        assertNotNull(result);
    }

    @Test
    void update_Validation_andSave() {
        Book updatedBook = new Book("Mio min Mio", "Astrid Lindgren", 1987, "Barnbok/äventyr");
        when(mockBookRepository.save(updatedBook)).thenReturn(updatedBook);
        when(mockBookRepository.findById(1L)).thenReturn(Optional.of(updatedBook));

        assertEquals(1L, updatedBook.getId());
        verify(mockBookValidator, times(1)).validate(updatedBook);
        verify(mockBookRepository, times(1)).save(updatedBook);
    }

    @Test
    void delete() {
        Book book = new Book();
        when(mockBookRepository.findById(1L)).thenReturn(Optional.of(book));

        testBookService.delete(1L);

        verify(mockBookRepository, times(1)).delete(book);
    }

    @Test
    void delete_ThrowsException() {
        when(mockBookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> testBookService.delete(1L));
        verify(mockBookRepository, never()).delete(any());
    }

}