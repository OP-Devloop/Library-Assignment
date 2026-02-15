package se.iths.oscarp.libraryassignment.service;

import org.springframework.stereotype.Service;
import se.iths.oscarp.libraryassignment.exception.AudiobookNotFoundException;
import se.iths.oscarp.libraryassignment.model.Audiobook;
import se.iths.oscarp.libraryassignment.repository.AudiobookRepository;

import java.util.List;

@Service
public class AudiobookService {
    private final AudiobookRepository audiobookRepository;

    public AudiobookService(AudiobookRepository audiobookRepository) {
        this.audiobookRepository = audiobookRepository;
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
        audiobookValidator.validate(audiobook);
        return audiobookRepository.save(audiobook);
    }

    public Audiobook updateAudiobook(Long id, Audiobook audiobook) {
        audiobookValidator.validate(audiobook);

        Audiobook existing = audiobookRepository.findById(id)
                .orElseThrow(() ->
                        new AudiobookNotFoundException("Audiobook id: " + id + " not found"));

        audiobook.setId(existing.getId());
        return audiobookRepository.save(audiobook);
    }

    public void deleteAudiobook(Long id) {
        Audiobook audiobook = audiobookRepository.findById(id)
                .orElseThrow(() ->
                        new AudiobookNotFoundException("Audiobook id: " + id + " not found"));

        audiobookRepository.delete(audiobook);
    }
}
