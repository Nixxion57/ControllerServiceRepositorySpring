package be.kdg.programming3.mangaStore.service;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.presentation.MangakaController;
import be.kdg.programming3.mangaStore.util.JsonWriter;
import be.kdg.programming3.mangaStore.repository.MangakaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile({"collection", "jpa", "jdbc"})
public class MangakaServiceImpl implements MangakaService{
    private MangakaRepository mangakaRepository;
    private JsonWriter jsonWriter;

    @Autowired
    public MangakaServiceImpl(MangakaRepository mangakaRepository) {
        this.mangakaRepository = mangakaRepository;
    }

    @Override
    public List<Mangaka> getMangakas() {
        return mangakaRepository.readMangakas();
    }

    @Override
    public List<Mangaka> getMangakaByNameAndGender(String name, char gender) {
        List<Mangaka> mangakas = mangakaRepository.readMangakas();
        if (name != null) {
            mangakas = mangakas.stream().filter(mangaka -> mangaka.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        }
        if (gender == 'M' || gender == 'F') {
            mangakas = mangakas.stream().filter(mangaka -> mangaka.getGender() == gender).collect(Collectors.toList());
        }
        return mangakas;
    }

    @Override
    public Mangaka getMangakaById(int id) {
        return mangakaRepository.findById(id);
    }

    @Override
    public Mangaka addMangaka(Mangaka mangaka) {
        return mangakaRepository.createMangaka(mangaka);
    }

    @Override
    public void deleteMangaka(int id) {
        mangakaRepository.deleteMangaka(id);
    }

    @Override
    public void writeMangakasToJson(List<Mangaka> mangakas) {
        jsonWriter.writeMangakas(mangakas);
    }
}
