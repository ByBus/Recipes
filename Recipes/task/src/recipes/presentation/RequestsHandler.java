package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.business.Mappable;
import recipes.business.models.IdDTO;
import recipes.business.models.RecipeDTO;
import recipes.business.models.RecipeEntity;
import recipes.persistence.RecipeService;

import javax.validation.Valid;


@RestController
public class RequestsHandler {
    private final RecipeService service;
    private final Mappable<RecipeDTO, RecipeEntity> mapper;

    @Autowired
    public RequestsHandler(RecipeService service, Mappable<RecipeDTO, RecipeEntity> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/api/recipe/{id}")
    public RecipeDTO getRecipe(@PathVariable long id) {
        RecipeEntity entity = service.getById(id);
        if (entity == null) {
            throw new RecipeNotFoundException("Recipe not found");
        }
        return mapper.mapToDTO(entity);
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<IdDTO> saveRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {
        RecipeEntity recipeEntity = mapper.mapToEntity(recipeDTO);
        RecipeEntity savedRecipe = service.save(recipeEntity);
        return new ResponseEntity<>(new IdDTO(savedRecipe.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable long id) {
        boolean isRecipeExists = service.isRecipeWithIdExists(id);
        if (isRecipeExists) {
            service.deleteById(id);
            return new ResponseEntity<>("Recipe deleted", HttpStatus.NO_CONTENT);
        } else {
            throw new RecipeNotFoundException("Recipe not found");
        }
    }
}
