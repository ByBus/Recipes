package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.memory.Savable;

@RestController
public class RequestsHandler {
    @Autowired
    Savable<Recipe> memory;

    @GetMapping("/api/recipe")
    public Recipe getRecipe() {
        return memory.restore();
    }

    @PostMapping("/api/recipe")
    public void saveRecipe(@RequestBody Recipe recipe) {
        memory.save(recipe);
    }

}
