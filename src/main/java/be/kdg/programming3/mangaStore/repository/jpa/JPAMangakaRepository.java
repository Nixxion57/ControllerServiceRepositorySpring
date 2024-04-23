package be.kdg.programming3.mangaStore.repository.jpa;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.repository.MangakaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Profile("jpa")
public class JPAMangakaRepository implements MangakaRepository {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Mangaka> readMangakas() {
        logger.info("Reading mangakas through JPA...");
        List<Mangaka> mangakas = em.createQuery("select mangaka from Mangaka mangaka").getResultList();
        return mangakas;
    }

    @Override
    public Mangaka findById(int id) {
        Mangaka mangaka = em.find(Mangaka.class, id);
        return mangaka;
    }

    @Override
    @Transactional
    public Mangaka createMangaka(Mangaka mangaka) {
        em.persist(mangaka);
        return mangaka;
    }

    @Override
    @Transactional
    public void updateMangaka(Mangaka mangaka) {
        em.merge(mangaka);
    }

    @Override
    @Transactional
    public void deleteMangaka(int id) {
        em.remove(em.find(Mangaka.class,id));
    }
}
