package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.PublisherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends CrudRepository<PublisherEntity, Long> {

    @Override
    List<PublisherEntity> findAll();

    @Override
    Optional<PublisherEntity> findById(Long id);
}
