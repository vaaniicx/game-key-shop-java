package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.PlacingDetailsEntity;
import at.vaaniicx.lap.model.entity.pk.PlacingDetailsPk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlacingDetailsRepository extends CrudRepository<PlacingDetailsEntity, PlacingDetailsPk> {

    @Override
    List<PlacingDetailsEntity> findAll();

    @Override
    Optional<PlacingDetailsEntity> findById(PlacingDetailsPk placingDetailsPk);

    List<PlacingDetailsEntity> findAllByPlacingId(Long id);
}
