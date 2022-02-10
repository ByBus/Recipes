package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipes.memory.Savable;
import recipes.models.IdDTO;
import recipes.models.Recipe;

@RestController
public class RequestsHandler {
    Savable<Recipe, Integer> memory;

    @Autowired
    public RequestsHandler(Savable<Recipe, Integer> memory) {
        this.memory = memory;
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        Recipe recipe = memory.restore(id);
        if (recipe == null) {
            throw new RecipeNotFoundException("Recipe not found");
        }
        return memory.restore(id);
    }

    @PostMapping("/api/recipe/new")
    public IdDTO saveRecipe(@RequestBody Recipe recipe) {
        int newId = memory.save(recipe);
        return new IdDTO(newId);
    }

}
