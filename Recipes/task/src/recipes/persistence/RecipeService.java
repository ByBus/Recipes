package recipes.persistence;

import recipes.business.models.RecipeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
