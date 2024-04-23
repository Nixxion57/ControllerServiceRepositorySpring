package be.kdg.programming3.mangaStore.service;

import be.kdg.programming3.mangaStore.domain.Genres;
import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.util.JsonWriter;
import be.kdg.programming3.mangaStore.repository.MangaRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Profile({"collection", "jpa", "jdbc"})
public class MangaServiceImpl implements MangaService {
    private MangaRepository mangaRepository;
    private JsonWriter jsonWriter;

    @Autowired
    public MangaServiceImpl(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
//        logger.debug("constructor mangaService implementation");
    }

    @Override
    public List<Manga> getMangas() {
        return mangaRepository.readMangas();
    }

    @Override
    public List<Manga> getMangasByPublisher(Publisher publisher) {
        return mangaRepository.readMangas().stream().filter(manga -> manga.getPublisher().equals(publisher)).toList();
    }

    @Override
    public List<Manga> getMangasForPublisher(int publisher_id) {
        return mangaRepository.findByPublisher(publisher_id);
    }

    @Override
    public Manga getMangaById(int id) {
        return mangaRepository.findById(id);
    }

    @Override
    public List<Publisher> getPublishers() {
        return mangaRepository.readMangas().stream().map(Manga::getPublisher).distinct().toList();
    }

    @Override
    public void deleteManga(int id) {
        mangaRepository.deleteManga(id);
    }


    @Override
    public Manga addManga(Manga manga) {
        return mangaRepository.createManga(manga);
    }

    @Override
    public void writeMangasToJson(List<Manga> mangas) {
        jsonWriter.writeMangas(mangas);
    }
}
