package se.iths.oscarp.libraryassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.oscarp.libraryassignment.model.Audiobook;
import se.iths.oscarp.libraryassignment.service.AudiobookService;

@Controller
@RequestMapping("/audiobooks")
public class AudiobookController {
    private final AudiobookService audiobookService;

    public AudiobookController(AudiobookService audiobookService) {
        this.audiobookService = audiobookService;
    }

    @GetMapping
    public String getAllAudiobooks(Model model) {
        model.addAttribute("audiobooks", audiobookService.getAllAudiobooks());
        return "audiobooks";
    }

    @GetMapping("/new")
    public String showCreateForm() {
        return "create-audiobook";
    }

    @PostMapping
    public String createAudiobook(@ModelAttribute Audiobook audiobook) {
        Audiobook audiobook1 = audiobookService.createAudiobook(audiobook);
        return "redirect:/audiobooks/";
    }

    @GetMapping("/{id}")
    public String getAudiobookById(@PathVariable Long id, Model model) {
        model.addAttribute("audiobook", audiobookService.getAudiobookById(id));
        return "audiobook";
    }

    @PutMapping("/{id}")
    public String updateAudiobook(@PathVariable Long id, @ModelAttribute Audiobook audiobook) {
        Audiobook audiobook2 = audiobookService.updateAudiobook(id, audiobook);
        return "redirect:/audiobooks";
    }

    @GetMapping("/{id}/edit")
    public String editAudiobook(@PathVariable Long id, Model model) {
        Audiobook audiobook = audiobookService.getAudiobookById(id);
        model.addAttribute("audiobook", audiobook);
        return "edit-audiobook";
    }

    @DeleteMapping("/{id}")
    public String deleteAudiobook(@PathVariable Long id) {
        audiobookService.deleteAudiobook(id);
        return "redirect:/audiobooks";
    }
}
