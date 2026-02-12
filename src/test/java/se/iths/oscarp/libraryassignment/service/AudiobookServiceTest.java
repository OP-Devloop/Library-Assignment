package se.iths.oscarp.libraryassignment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.oscarp.libraryassignment.exception.AudiobookNotFoundException;
import se.iths.oscarp.libraryassignment.model.Audiobook;
import se.iths.oscarp.libraryassignment.repository.AudiobookRepository;
import se.iths.oscarp.libraryassignment.validator.AudiobookValidator;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AudiobookServiceTest {

    @Mock
    private AudiobookRepository audiobookRepository;

    @Mock
    private AudiobookValidator audiobookValidator;

    @InjectMocks
    private AudiobookService audiobookService;

    @Test
    void shouldReturnAllAudiobooks() {
        Audiobook book1 = new Audiobook();
        Audiobook book2 = new Audiobook();

        when(audiobookRepository.findAll())
                .thenReturn(List.of(book1, book2));

        List<Audiobook> result = audiobookService.getAllAudiobooks();

        assertThat(result).hasSize(2);
        verify(audiobookRepository).findAll();
    }

    @Test
    void shouldReturnAudiobookWhenIdExists() {
        Audiobook book = new Audiobook();
        book.setId(1L);

        when(audiobookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        Audiobook result = audiobookService.getAudiobookById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        verify(audiobookRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenAudiobookNotFound() {
        when(audiobookRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> audiobookService.getAudiobookById(1L))
                .isInstanceOf(AudiobookNotFoundException.class)
                .hasMessageContaining("1");
    }

    @Test
    void shouldValidateAndSaveAudiobook() {
        Audiobook book = new Audiobook();

        when(audiobookRepository.save(book)).thenReturn(book);

        Audiobook result = audiobookService.createAudiobook(book);

        verify(audiobookValidator).validate(book);
        verify(audiobookRepository).save(book);
        assertThat(result).isEqualTo(book);
    }

    @Test
    void shouldUpdateAudiobookWhenIdExists() {
        Audiobook existing = new Audiobook();
        existing.setId(1L);

        Audiobook updated = new Audiobook();

        when(audiobookRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(audiobookRepository.save(updated))
                .thenReturn(updated);

        Audiobook result = audiobookService.updateAudiobook(1L, updated);

        verify(audiobookValidator).validate(updated);
        verify(audiobookRepository).findById(1L);
        verify(audiobookRepository).save(updated);

        assertThat(result).isEqualTo(updated);
    }

    @Test
    void shouldThrowWhenUpdatingNonExistingAudiobook() {
        Audiobook updated = new Audiobook();

        when(audiobookRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                audiobookService.updateAudiobook(1L, updated))
                .isInstanceOf(AudiobookNotFoundException.class);
    }


    @Test
    void shouldDeleteAudiobookWhenExists() {
        Audiobook book = new Audiobook();
        book.setId(1L);

        when(audiobookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        audiobookService.deleteAudiobook(1L);

        verify(audiobookRepository).delete(book);
    }

    @Test
    void shouldThrowWhenDeletingNonExistingAudiobook() {
        when(audiobookRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                audiobookService.deleteAudiobook(1L))
                .isInstanceOf(AudiobookNotFoundException.class);
    }
}