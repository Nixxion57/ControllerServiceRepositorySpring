package be.kdg.programming3.mangaStore.service.springData;

import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.repository.springDataJpa.SpringDataJpaMangaRepo;
import be.kdg.programming3.mangaStore.repository.springDataJpa.SpringDataJpaMangakaRepo;
import be.kdg.programming3.mangaStore.repository.springDataJpa.SpringDataPublisherRepo;
import be.kdg.programming3.mangaStore.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Profile("springdata")
public class SpringDataMangaService implements MangaService {
    private SpringDataJpaMangaRepo mangaRepo;
    private SpringDataJpaMangakaRepo mangakaRepo;
    private SpringDataPublisherRepo publisherRepo;

    @Autowired
    public SpringDataMangaService(SpringDataJpaMangaRepo mangaRepo, SpringDataJpaMangakaRepo mangakaRepo, SpringDataPublisherRepo publisherRepo) {
        this.mangaRepo = mangaRepo;
        this.mangakaRepo = mangakaRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<Manga> getMangas() {
        return mangaRepo.findAll();
    }

    @Override
    public List<Manga> getMangasByPublisher(Publisher publisher) {
        return null;
    }

    @Override
    public List<Manga> getMangasForPublisher(int publisher_id) {
        return mangaRepo.findByPublisher(publisher_id);
    }

    @Override
    public Manga getMangaById(int id) {
        return mangaRepo.findById(id).get();
    }

    @Override
    public List<Publisher> getPublishers() {
        return null;
    }

    @Override
    public void deleteManga(int id) {
        mangaRepo.deleteById(id);
    }

    @Override
    public Manga addManga(Manga manga) {
        return mangaRepo.save(manga);
    }

    @Override
    public void writeMangasToJson(List<Manga> mangas) {

    }
//
//    @Transactional
//    public void setPublisherOfManga(int manga_id, int publisher_id) {
//        Manga manga = mangaRepo.findById(manga_id).get();
//        if (manga != null) {
//            Publisher publisher = publisherRepo.findById(publisher_id).get();
//            if (publisher != null) {
//                manga.setPublisher(publisher);
//                mangaRepo.save(manga);
//            }
//        }
//    }


}
