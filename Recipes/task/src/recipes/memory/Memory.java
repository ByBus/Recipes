package recipes.memory;

import org.springframework.stereotype.Component;
import recipes.models.Recipe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Memory implements Savable<Recipe, Integer> {
    private final Map<Integer, Recipe> recipes = new ConcurrentHashMap<>();
    private int currentId = 0;

    @Override
    public Integer save(Recipe recipe) {
        recipes.put(currentId, recipe);
        return currentId++;
    }

    @Override
    public Recipe restore(Integer id) {
        return recipes.get(id);
    }
}
