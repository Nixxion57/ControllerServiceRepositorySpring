package be.kdg.programming3.mangaStore.util;

import be.kdg.programming3.mangaStore.domain.Genres;
import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.repository.MangaRepository;
import be.kdg.programming3.mangaStore.repository.MangakaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("collection")
public class DataFactory implements CommandLineRunner {
    private MangaRepository mangaRepository;
    private MangakaRepository mangakaRepository;

    public DataFactory(MangaRepository mangaRepository, MangakaRepository mangakaRepository) {
        this.mangaRepository = mangaRepository;
        this.mangakaRepository = mangakaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Manga manga1 = new Manga("One Piece", "Eiichiro Oda", 1062, LocalDate.of(2001, 8, 11), Genres.SHONEN, 12.5);
        Manga manga2 = new Manga("Naruto", "Masashi Kishimoto", 700, LocalDate.of(2002, 12, 16), Genres.ACTION, 10);
        Manga manga3 = new Manga("Bleach", "Tite Kubo", 686, LocalDate.of(2003, 10, 20), Genres.FANTASY, 13.6);
        Manga manga4 = new Manga("Nisekoi", "Naoshi Komi", 282, LocalDate.of(2004, 6, 2), Genres.ROMANCE, 11.3);
        Manga manga5 = new Manga("Berserk", "Kentaro Miura", 370, LocalDate.of(2003, 3, 29), Genres.HORROR, 10.99);
        Manga manga6 = new Manga("Jisatsutou", "Kouji Mori", 168, LocalDate.of(2002, 4, 1), Genres.HORROR, 11.99);

        List<Manga> mangas = new ArrayList<>();
        mangas.add(manga1);
        mangas.add(manga2);
        mangas.add(manga3);
        mangas.add(manga4);
        mangas.add(manga5);
        mangas.add(manga6);

        Mangaka mangaka1 = new Mangaka("Eiichiro Oda", 'M', 47);
        Mangaka mangaka2 = new Mangaka("Kouji Mori", 'M', 55);
        Mangaka mangaka3 = new Mangaka("Masashi Kishimoto", 'M', 47);
        Mangaka mangaka4 = new Mangaka("Tite Kubo", 'M', 45);
        Mangaka mangaka5 = new Mangaka("Naoshi Komi", 'M', 36);
        Mangaka mangaka6 = new Mangaka("Mikio Ikemoto", 'M', 45);
        Mangaka mangaka7 = new Mangaka("Kentaro Miura", 'M', 54);

        List<Mangaka> mangakas = new ArrayList<>();
        mangakas.add(mangaka1);
        mangakas.add(mangaka2);
        mangakas.add(mangaka3);
        mangakas.add(mangaka4);
        mangakas.add(mangaka5);
        mangakas.add(mangaka6);
        mangakas.add(mangaka7);
        manga2.addMangaka(mangaka6);
        manga2.addMangaka(mangaka3);
        mangaka6.addManga(manga2);
        mangaka3.addManga(manga2);
        manga5.addMangaka(mangaka7);
        manga5.addMangaka(mangaka2);
        mangaka7.addManga(manga5);
        mangaka2.addManga(manga5);
        manga6.addMangaka(mangaka2);
        mangaka2.addManga(manga6);
        manga1.addMangaka(mangaka1);
        mangaka1.addManga(manga1);
        manga3.addMangaka(mangaka4);
        mangaka4.addManga(manga3);
        manga4.addMangaka(mangaka5);
        mangaka5.addManga(manga4);

        Publisher publisher1 = new Publisher("Shueisha", "Tokyo", LocalDate.of(1926, 8, 8));
        Publisher publisher2 = new Publisher("Konami", "Tokyo", LocalDate.of(1969, 3, 21));
        Publisher publisher3 = new Publisher("Hakusensha", "Tokyo", LocalDate.of(1973, 12, 1));

        manga1.setPublisher(publisher1);
        manga2.setPublisher(publisher1);
        manga3.setPublisher(publisher1);
        publisher1.addManga(manga1);
        publisher1.addManga(manga2);
        publisher1.addManga(manga3);
        manga4.setPublisher(publisher2);
        publisher2.addManga(manga4);
        manga5.setPublisher(publisher3);
        manga6.setPublisher(publisher3);
        publisher3.addManga(manga5);
        publisher3.addManga(manga6);

        mangas.forEach(mangaRepository::createManga);
        mangakas.forEach(mangakaRepository::createMangaka);

    }
}
