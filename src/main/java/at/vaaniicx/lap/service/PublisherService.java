package at.vaaniicx.lap.service;

import at.vaaniicx.lap.model.entity.PublisherEntity;
import at.vaaniicx.lap.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<PublisherEntity> getAllPublisher() {
        return publisherRepository.findAll();
    }
}
