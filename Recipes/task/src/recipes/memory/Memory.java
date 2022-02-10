package recipes.memory;

import recipes.Recipe;

public class Memory implements Savable<Recipe> {
    private Recipe saved;

    @Override
    public void save(Recipe recipe) {
        saved = recipe;
    }

    @Override
    public Recipe restore() {
        return saved;
    }
}
