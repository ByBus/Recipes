package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import recipes.business.Mappable;
import recipes.persistence.RepositoryService;
import recipes.persistence.models.*;
import recipes.presentation.exceptions.BadRequestException;
import recipes.presentation.exceptions.ForbiddenException;
import recipes.presentation.exceptions.RecipeNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;


@RestController
public class RequestsHandler {
    private final RepositoryService service;
    private final Mappable<RecipeDTO, RecipeEntity> mapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public RequestsHandler(RepositoryService service, Mappable<RecipeDTO, RecipeEntity> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/api/recipe/{id}")
    public RecipeDTO getRecipe(@PathVariable long id) {
        RecipeEntity entity = service.getRecipeById(id);
        if (entity == null) {
            throw new RecipeNotFoundException("Recipe not found");
        }
        return mapper.mapToDTO(entity);
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> saveRecipe(@AuthenticationPrincipal UserDetails details,
                                        @Valid @RequestBody RecipeDTO recipeDTO) {
        UserEntity user = service.getUserByEmail(details.getUsername());
        RecipeEntity recipe = service.updateUserRecipe(user, mapper.mapToEntity(recipeDTO));
        return new ResponseEntity<>(new IdDTO(recipe.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@AuthenticationPrincipal UserDetails details,
                                               @PathVariable long id) {
        return evaluateUpdateOrDelete(details, id, (user, recipe) -> user.getRecipes().remove(recipe));
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<String> updateRecipe(@AuthenticationPrincipal UserDetails details,
                                               @Valid @RequestBody RecipeDTO recipeDTO,
                                               @PathVariable long id) {
        return evaluateUpdateOrDelete(details, id, (user, recipe) -> mapper.update(recipeDTO, recipe));
    }

    @GetMapping("/api/recipe/search")
    public List<RecipeDTO> filterRecipes(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String category) {
        if ((name != null) == (category != null)) {
            throw new BadRequestException("Bad request");
        }
        List<RecipeEntity> entities = name != null ? service.findRecipesByName(name) : service.findRecipesByCategory(category);
        return entities.stream()
                .map(mapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody RegistrationDTO registration) {
        UserEntity userEntity = service.getUserByEmail(registration.getEmail());
        if (userEntity == null) {
            UserEntity newUser = new UserEntity(registration.getEmail(), encoder.encode(registration.getPassword()));
            service.saveUser(newUser);
            return new ResponseEntity<>("User Registered", HttpStatus.OK);
        } else {
            throw new BadRequestException("Bad request");
        }
    }

    private ResponseEntity<String> evaluateUpdateOrDelete(UserDetails details,
                                                          long id,
                                                          BiConsumer<UserEntity, RecipeEntity> function) {
        if (!service.isRecipeWithIdExists(id)) {
            throw new RecipeNotFoundException("Recipe not found");
        }
        String username = details.getUsername();
        UserEntity user = service.getUserByEmail(username);
        RecipeEntity recipe = user.getRecipes().stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        if (recipe != null) {
            function.accept(user, recipe);
            service.saveUser(user);
            return ResponseEntity.noContent().build();
        } else {
            throw new ForbiddenException("Forbidden");
        }
    }
}
