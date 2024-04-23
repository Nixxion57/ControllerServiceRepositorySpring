package be.kdg.programming3.mangaStore.domain;

import be.kdg.programming3.mangaStore.util.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Manga")
public class Manga {

    //    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //    @Column(unique = true)
    private String title;

    //    @Column(unique = true)
    private String author;

    //    @Column(unique = true)
    private int chapters;

    //    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Genres genre;

    @Column(name = "written")
    private LocalDate written;

    @Column(name = "pricepercopy")
    private double pricePerCopy;

    @ManyToMany
    @JoinTable(name = "Ownership")
    private transient List<Mangaka> mangaka = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


    public Manga(String title, String author, int chapters, LocalDate written, Genres genre, double pricePerCopy) {
        this.title = title;
        this.author = author;
        this.chapters = chapters;
        this.written = written;
        this.genre = genre;
        this.pricePerCopy = pricePerCopy;
    }

    public Manga(int id, String title, String author, int chapters, LocalDate written, Genres genre, double pricePerCopy) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.chapters = chapters;
        this.written = written;
        this.genre = genre;
        this.pricePerCopy = pricePerCopy;
    }

    protected Manga() {
        System.out.println("Creating the manga proxy...");
    }

//    public Manga() {
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public Genres getGenre() {
        return genre;
    }

    public LocalDate getWritten() {
        return written;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public double getPricePerCopy() {
        return pricePerCopy;
    }

    public void setPricePerCopy(double pricePerCopy) {
        this.pricePerCopy = pricePerCopy;
    }

    public List<Mangaka> getMangaka() {
        return mangaka;
    }

    public void setMangaka(List<Mangaka> mangaka) {
        this.mangaka = mangaka;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void addMangaka(Mangaka mangaka) {
        if (this.mangaka == null) this.mangaka = new ArrayList<>();
        this.mangaka.add(mangaka);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        return "Manga{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", chapters=" + chapters +
                ", written=" + dtf.format(written) +
                ", genre=" + genre +
                ", pricePerCopy=" + pricePerCopy +
                '}';
    }
}
