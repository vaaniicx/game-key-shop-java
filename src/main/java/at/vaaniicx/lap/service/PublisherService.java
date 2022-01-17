package at.vaaniicx.lap.service;

import at.vaaniicx.lap.exception.KeyCodeNotFoundException;
import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<PublisherEntity> getAllPublisher() {
        return publisherRepository.findAll();
    }

    public PublisherEntity getPublisherById(Long id) {
        Optional<PublisherEntity> entity = publisherRepository.findById(id);

        if (!entity.isPresent()) {
            throw new KeyCodeNotFoundException();
        }

        return entity.get();
    }

    public PublisherEntity registerPublisher(String name) {
        return publisherRepository.save(PublisherEntity.builder().publisher(name).build());
    }

    public PublisherEntity updatePublisher(PublisherEntity e) {
        return publisherRepository.save(e);
    }

    public void deletePublisherById(Long id) {
        publisherRepository.deleteById(id);
    }
}
