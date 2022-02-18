package recipes.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String category;

    private LocalDateTime date;
    @NotBlank
    private String description;
    @NotEmpty
    private String[] ingredients;
    @NotEmpty
    private String[] directions;
}
