package be.kdg.programming3.mangaStore.service;

import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"hardcoded", "jpa", "jdbc"})
public class PublisherServiceImpl implements PublisherService{
    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getPublishers() {
        return publisherRepository.readPublishers();
    }

    @Override
    public Publisher getPublisherById(int id) {
        return publisherRepository.findById(id);
    }

    @Override
    public void deletePublisher(int id) {
        publisherRepository.deletePublisher(id);
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        return publisherRepository.createPublisher(publisher);
    }
}
