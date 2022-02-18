package recipes.business;

import recipes.persistence.models.RecipeDTO;
import recipes.persistence.models.RecipeEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RecipeMapper implements Mappable<RecipeDTO, RecipeEntity> {

    @Override
    public RecipeEntity mapToEntity(RecipeDTO recipe) {
        return new RecipeEntity(recipe.getName(),
                recipe.getCategory(),
                recipe.getDescription(),
                recipe.getIngredients(),
                recipe.getDirections());
    }

    @Override
    public RecipeDTO mapToDTO(RecipeEntity recipe) {
        return new RecipeDTO(recipe.getName(),
                recipe.getCategory(),
                recipe.getDate(),
                recipe.getDescription(),
                recipe.getIngredients(),
                recipe.getDirections());
    }

    @Override
    public void update(RecipeDTO from, RecipeEntity to) {
        to.setName(from.getName());
        to.setCategory(from.getCategory());
        to.setDate(LocalDateTime.now());
        to.setDescription(from.getDescription());
        to.setIngredients(from.getIngredients());
        to.setDirections(from.getDirections());
    }
}
