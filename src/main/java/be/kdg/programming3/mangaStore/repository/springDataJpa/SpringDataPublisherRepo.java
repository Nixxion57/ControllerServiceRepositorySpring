package be.kdg.programming3.mangaStore.repository.springDataJpa;

import be.kdg.programming3.mangaStore.domain.Publisher;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("springdata")
public interface SpringDataPublisherRepo extends JpaRepository<Publisher, Integer> {
}
