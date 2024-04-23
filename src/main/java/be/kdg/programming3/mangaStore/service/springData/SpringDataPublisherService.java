package be.kdg.programming3.mangaStore.service.springData;

import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.repository.PublisherRepository;
import be.kdg.programming3.mangaStore.repository.springDataJpa.SpringDataPublisherRepo;
import be.kdg.programming3.mangaStore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdata")
public class SpringDataPublisherService implements PublisherService {
    private SpringDataPublisherRepo publisherRepo;

    @Autowired
    public SpringDataPublisherService(SpringDataPublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<Publisher> getPublishers() {
        return publisherRepo.findAll();
    }

    @Override
    public Publisher getPublisherById(int id) {
        return publisherRepo.findById(id).get();
    }

    @Override
    public void deletePublisher(int id) {
        publisherRepo.deleteById(id);
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        return publisherRepo.save(publisher);
    }
}
