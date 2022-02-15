package recipes.business;

import recipes.business.models.RecipeDTO;
import recipes.business.models.RecipeEntity;
import org.springframework.stereotype.Component;

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
}
