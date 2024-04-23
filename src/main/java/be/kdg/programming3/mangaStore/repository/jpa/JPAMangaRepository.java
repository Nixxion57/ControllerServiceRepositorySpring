package be.kdg.programming3.mangaStore.repository.jpa;

import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.repository.MangaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Profile("jpa")
public class JPAMangaRepository implements MangaRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Manga> readMangas() {
        List<Manga> mangas = em.createQuery("select manga from Manga manga", Manga.class).getResultList();
        return mangas;
    }

    @Override
    public Manga findById(int id) {
        Manga manga = em.find(Manga.class, id);
        return manga;
    }

    @Override
    @Transactional
    public Manga createManga(Manga manga) {
        em.persist(manga);
        return manga;
    }

    @Override
    @Transactional
    public void updateManga(Manga manga) {
        em.merge(manga);
    }

    @Override
    @Transactional
    public void deleteManga(int id) {
        em.remove(em.find(Manga.class,id));
    }

    @Override
    public List<Manga> findByMangaka(int mangakaId) {
        return null;
    }

    @Override
    public List<Manga> findByPublisher(int publisherId) {
        return null;
    }


}
