package recipes.persistence;

import recipes.business.models.RecipeDTO;
import recipes.business.models.RecipeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository repository;

    @Autowired
    public RecipeService(RecipeRepository repository) {

        this.repository = repository;
    }

    public RecipeEntity getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public RecipeEntity save(RecipeEntity recipe) {
        return repository.save(recipe);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean isRecipeWithIdExists(Long id) {
        return repository.existsById(id);
    }

    public void updateRecipe(Long id, RecipeDTO recipeDTO) {
        RecipeEntity entity = getById(id);
        entity.setName(recipeDTO.getName());
        entity.setCategory(recipeDTO.getCategory());
        entity.setDate(LocalDateTime.now());
        entity.setDescription(recipeDTO.getDescription());
        entity.setIngredients(recipeDTO.getIngredients());
        entity.setDirections(recipeDTO.getDirections());
        save(entity);
    }

    public List<RecipeEntity> findByName(String name) {
        return repository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public List<RecipeEntity> findByCategory(String findByCategory) {
        return repository.findByCategoryIgnoreCaseOrderByDateDesc(findByCategory);
    }
}
