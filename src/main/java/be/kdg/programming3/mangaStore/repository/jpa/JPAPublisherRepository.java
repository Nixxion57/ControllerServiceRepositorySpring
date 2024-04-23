package be.kdg.programming3.mangaStore.repository.jpa;

import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.repository.PublisherRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Profile("jpa")
public class JPAPublisherRepository implements PublisherRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Publisher> readPublishers() {
        List<Publisher> publishers = em.createQuery("select publisher from Publisher publisher").getResultList();
        return publishers;
    }

    @Override
    public Publisher findById(int id) {
        Publisher publisher = em.find(Publisher.class,id);
        return publisher;
    }

    @Override
    @Transactional
    public Publisher createPublisher(Publisher publisher) {
        em.persist(publisher);
        return publisher;
    }

    @Override
    @Transactional
    public void updatePublisher(Publisher publisher) {
        em.merge(publisher);
    }

    @Override
    @Transactional
    public void deletePublisher(int id) {
        em.remove(em.find(Publisher.class, id));
    }
}
