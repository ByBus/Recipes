package recipes.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.business.models.RecipeEntity;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    List<RecipeEntity> findByNameContainingIgnoreCaseOrderByDateDesc(String name);

    List<RecipeEntity> findByCategoryIgnoreCaseOrderByDateDesc(String category);
}