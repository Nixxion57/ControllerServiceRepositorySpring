package be.kdg.programming3.mangaStore.service;

import be.kdg.programming3.mangaStore.domain.Genres;
import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.domain.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface MangaService {

    List<Manga> getMangas();
    List<Manga> getMangasByPublisher(Publisher publisher);
    List<Manga> getMangasForPublisher(int publisher_id);
    Manga getMangaById(int id);
    List<Publisher> getPublishers();
    void deleteManga(int id);
    Manga addManga(Manga manga);
//    Manga addManga(String title, String author, int chapters, LocalDate written, Genres genre, double pricePerCopy );
    void writeMangasToJson(List<Manga> mangas);
}
