package recipes.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.business.models.RecipeEntity;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
}