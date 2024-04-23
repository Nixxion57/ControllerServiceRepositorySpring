package be.kdg.programming3.mangaStore.service.springData;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.repository.springDataJpa.SpringDataJpaMangaRepo;
import be.kdg.programming3.mangaStore.repository.springDataJpa.SpringDataJpaMangakaRepo;
import be.kdg.programming3.mangaStore.service.MangakaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Profile("springdata")
public class SpringDataMangakaService implements MangakaService {
    private SpringDataJpaMangakaRepo mangakaRepo;
    private SpringDataJpaMangaRepo mangaRepo;


    @Autowired
    public SpringDataMangakaService(SpringDataJpaMangakaRepo mangakaRepo, SpringDataJpaMangaRepo mangaRepo) {
        this.mangakaRepo = mangakaRepo;
        this.mangaRepo = mangaRepo;
    }


    @Override
    public List<Mangaka> getMangakas() {
        return mangakaRepo.findAll();
    }

    @Override
    public List<Mangaka> getMangakaByNameAndGender(String name, char gender) {
        return null;
    }

    @Override
    public Mangaka getMangakaById(int id) {
        return mangakaRepo.findById(id).get();
    }

    @Override
    public Mangaka addMangaka(Mangaka mangaka) {
        return mangakaRepo.save(mangaka);
    }

    @Override
    @Transactional
    public void deleteMangaka(int id) {
        Mangaka mangaka = mangakaRepo.findById(id).get();
        mangaka.getMangas().forEach(manga -> {
            manga.getMangaka().remove(mangaka);
            mangaRepo.save(manga);
        });
        mangakaRepo.deleteById(id);
    }

    @Override
    public void writeMangakasToJson(List<Mangaka> mangakas) {

    }
}
