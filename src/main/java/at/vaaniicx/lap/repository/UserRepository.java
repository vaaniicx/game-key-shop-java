package at.vaaniicx.lap.repository;

import at.vaaniicx.lap.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Override
    List<UserEntity> findAll();

    @Override
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPersonId(Long id);

    List<UserEntity> findByRoleId(Long id);
}
