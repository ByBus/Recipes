package recipes.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.persistence.models.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> { }
