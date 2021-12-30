package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    @Override
    List<AddressEntity> findAll();

    @Override
    Optional<AddressEntity> findById(Long id);
}
