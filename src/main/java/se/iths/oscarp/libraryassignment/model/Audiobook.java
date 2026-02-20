package se.iths.oscarp.libraryassignment.model;

import jakarta.persistence.*;

@Entity
public class Audiobook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    private String author;

    private String narrator;

    @Column(name = "release_date")
    private String releaseDate;

    public Audiobook() {
    }

    public Audiobook(Long id, String title, String category, String author, String narrator, String releaseDate) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.author = author;
        this.narrator = narrator;
        this.releaseDate = releaseDate;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
