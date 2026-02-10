package se.iths.oscarp.libraryassignment.service;

import org.springframework.stereotype.Service;
import se.iths.oscarp.libraryassignment.exception.AudiobookNotFoundException;
import se.iths.oscarp.libraryassignment.model.Audiobook;
import se.iths.oscarp.libraryassignment.repository.AudiobookRepository;
import se.iths.oscarp.libraryassignment.validator.AudiobookValidator;

import java.util.List;

@Service
public class AudiobookService {
    private final AudiobookRepository audiobookRepository;
    private final AudiobookValidator audiobookValidator;

    public AudiobookService(AudiobookRepository audiobookRepository, AudiobookValidator audiobookValidator) {
        this.audiobookRepository = audiobookRepository;
        this.audiobookValidator = audiobookValidator;
    }

    public List<Audiobook> getAllAudiobooks() {
        return audiobookRepository.findAll();
    }

    public Audiobook getAudiobookById(Long id) {
        return audiobookRepository.findById(id)
                .orElseThrow(() ->
                        new AudiobookNotFoundException("Audiobook id: " + id + " not found"));
    }

    public Audiobook createAudiobook(Audiobook audiobook) {
        return audiobookRepository.save(audiobook);
    }

    public Audiobook updateAudiobook(Long id, Audiobook audiobook) {
        if (audiobookRepository.existsById(id)) {
            return audiobookRepository.save(audiobook);
        } else {
            throw new AudiobookNotFoundException("Audiobook id: " + id + " not found");
        }
    }

    public void deleteAudiobook(Long id) {
        if (audiobookRepository.existsById(id)) {
            audiobookRepository.deleteById(id);
        } else {
            throw new AudiobookNotFoundException("Audiobook id: " + id + " not found");
        }

    }
}
