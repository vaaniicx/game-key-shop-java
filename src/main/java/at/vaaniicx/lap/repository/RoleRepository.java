package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    @Override
    List<RoleEntity> findAll();

    @Override
    Optional<RoleEntity> findById(Long id);
}
