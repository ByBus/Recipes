package recipes.persistence;

import recipes.persistence.models.RecipeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.persistence.models.UserEntity;

import java.util.List;

@Service
public class RepositoryService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public RepositoryService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public RecipeEntity getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public boolean isRecipeWithIdExists(Long id) {
        return recipeRepository.existsById(id);
    }

    public List<RecipeEntity> findRecipesByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public List<RecipeEntity> findRecipesByCategory(String findByCategory) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(findByCategory);
    }

    // User

    public UserEntity getUserByEmail(String email) {
        return userRepository.findById(email).orElse(null);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public RecipeEntity updateUserRecipe(UserEntity user, RecipeEntity recipe) {
        user.getRecipes().add(recipe);
        user = saveUser(user);
        List<RecipeEntity> allRecipes = user.getRecipes();
        return allRecipes.get(allRecipes.size() - 1);
    }
}
