package be.kdg.programming3.mangaStore.presentation.viewModel;

import be.kdg.programming3.mangaStore.domain.Genres;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Profile("web")
public class MangaViewModel {
    private int id;

    @NotBlank(message = "This field is mandatory!")
    @Size(min = 2, max = 50, message = "The length should be between 2 and 50 characters!")
    private String title;
    private String author;
    private int chapters;
    private @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate written;
    private Genres genre;
    private Double pricePerCopy;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getChapters() {
        return chapters;
    }

    public LocalDate getWritten() {
        return written;
    }

    public Genres getGenre() {
        return genre;
    }

    public Double getPricePerCopy() {
        return pricePerCopy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public void setWritten(LocalDate written) {
        this.written = written;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public void setPricePerCopy(Double pricePerCopy) {
        this.pricePerCopy = pricePerCopy;
    }

    @Override
    public String toString() {
        return "MangaViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", chapters=" + chapters +
                ", written=" + written +
                ", genre=" + genre +
                ", pricePerCopy=" + pricePerCopy +
                '}';
    }
}
